package com.example.tbcacademyhomework.data.users.repository

import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import com.example.tbcacademyhomework.domain.users.models.PagedUsers
import com.example.tbcacademyhomework.domain.users.usecase.GetLocalUsersUseCase
import com.example.tbcacademyhomework.domain.users.usecase.GetRemoteUsersUseCase
import com.example.tbcacademyhomework.domain.users.usecase.UpdateLocalUsersUseCase
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


interface GetPagedUsersUseCase {
    suspend operator fun invoke(page: Int): Flow<Resource<PagedUsers, DataError>>
}


class GetPagedUsersUseCaseImpl @Inject constructor(
    private val getLocalUsersUseCase: GetLocalUsersUseCase,
    private val updateLocalUsersUseCase: UpdateLocalUsersUseCase,
    private val getRemoteUsersUseCase: GetRemoteUsersUseCase,
    private val connectivityObserver: ConnectivityObserver
) : GetPagedUsersUseCase, CoroutineScope {


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

    override suspend operator fun invoke(page: Int): Flow<Resource<PagedUsers, DataError>> {
        return channelFlow {
            val totalPg = totalPages
            if (totalPg == null || totalPg >= page) {
                send(Resource.Loading)
                coroutineScope {
                    launch {
                        val loadedUsers = getLocalUsersUseCase(page)
                        if (loadedUsers.isNotEmpty()) {
                            send(Resource.Success(PagedUsers(false, loadedUsers)))
                        }
                    }

                    launch {
                        if (hasInternetConnection) {
                            networkErrorSent = false
                            val remoteUsers = getRemoteUsersUseCase(page)

                            if (remoteUsers is Resource.Success) {
                                val responseData = remoteUsers.data
                                val updatedUsers = updateLocalUsersUseCase(page, responseData.data)
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

                        }
                    }
                }
            }


        }

    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main.immediate
}