package com.example.tbcacademyhomework.data.users.repository

import com.example.tbcacademyhomework.data.core.connectivity_observer.NetworkConnectivityObserver
import com.example.tbcacademyhomework.domain.users.models.PagedUsers
import com.example.tbcacademyhomework.domain.users.repository.OfflineFirstUsersRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersRemoteRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class OfflineFirstUsersRepositoryImpl @Inject constructor(
    private val usersLocalRepository: UsersLocalRepository,
    private val usersRemoteRepository: UsersRemoteRepository,
    private val connectivityObserver: NetworkConnectivityObserver
) : OfflineFirstUsersRepository, CoroutineScope {


    private var hasInternetConnection = true
    private var totalPages: Int? = null

    init {
        launch {
            connectivityObserver.isConnected.collectLatest {
                hasInternetConnection = it
            }
        }
    }


    private var networkErrorSent = false

    private val eventChannel = Channel<Resource<Nothing, DataError>>()

    override val eventFlow: Flow<Resource<Nothing, DataError>>
        get() = eventChannel.receiveAsFlow()

    override suspend fun fetchUsers(page: Int): Flow<Resource<PagedUsers, DataError>> {
        return channelFlow {
            val totalPg = totalPages
            if (totalPg == null || totalPg >= page) {
                send(Resource.Loading)
                coroutineScope {
                    launch {
                        val locallyLoadedPages = usersLocalRepository.getLoadedPageCount()
                        val loadedUsers = usersLocalRepository.getUsers(page)

                        val hasReachedEnd =
                            if (hasInternetConnection) false else locallyLoadedPages == page
                        if (loadedUsers.isNotEmpty()) {
                            send(Resource.Success(PagedUsers(hasReachedEnd, loadedUsers)))
                        }
                    }

                    launch {
                        if (hasInternetConnection) {
                            networkErrorSent = false
                            when (val remoteUsers = usersRemoteRepository.getUsers(page)) {
                                is Resource.Success -> {
                                    val responseData = remoteUsers.data
                                    val updatedUsers =
                                        usersLocalRepository.updateUsers(page, responseData.data)
                                    totalPages = responseData.totalPages
                                    send(
                                        Resource.Success(
                                            PagedUsers(
                                                endReached = responseData.page >= responseData.totalPages,
                                                users = updatedUsers
                                            )
                                        )
                                    )
                                }

                                is Resource.Error -> {
                                    if (!networkErrorSent) {
                                        eventChannel.send(remoteUsers)
                                        networkErrorSent = true
                                    }
                                }

                                Resource.Loading -> Unit
                            }
                        } else {
                            if (!networkErrorSent) {
                                eventChannel.send(Resource.Error(DataError.DynamicError.NO_INTERNET))
                                networkErrorSent = true
                            }
                        }
                    }
                }
            }


        }

    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate
}