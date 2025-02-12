@file:OptIn(ExperimentalPagingApi::class)

package com.example.tbcacademyhomework.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.tbcacademyhomework.data.local.database.AppDatabase
import com.example.tbcacademyhomework.data.local.database.UserEntity
import com.example.tbcacademyhomework.data.local.database.UserRemoteKeysEntity
import com.example.tbcacademyhomework.data.mappers.toUserEntity
import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import com.example.tbcacademyhomework.domain.remote.repository.UsersRemoteSource
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UsersPagingSource(
    private val appDatabase: AppDatabase,
    private val usersRemoteSource: UsersRemoteSource,
    private val connectivityObserver: ConnectivityObserver
) : RemoteMediator<Int, UserEntity>() {

    private val userDao = appDatabase.dao
    private val userKeysDao = appDatabase.remoteKeysDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val isConnected = connectivityObserver.isConnected.first()
            if (isConnected){

                withContext(Dispatchers.IO) {

                    when (val response = usersRemoteSource.getUsers(currentPage)) {
                        is Resource.Success -> {
                            val responseBody = response.data
                            val users = responseBody.data
                            val isEnd = responseBody.totalPages == currentPage

                            val prevPage = if (currentPage == 1) null else currentPage - 1
                            val nextPage = if (isEnd) null else currentPage + 1
                            appDatabase.withTransaction {
                                if (loadType == LoadType.REFRESH) {
                                    userDao.clearAll()
                                    userKeysDao.deleteAllRemoteKeys()
                                }
                                userDao.upsertUsers(users.map { it.toUserEntity() })
                                val keys = responseBody.data.map { user ->
                                    UserRemoteKeysEntity(
                                        id = user.id,
                                        prevPage = prevPage,
                                        nextPage = nextPage
                                    )
                                }
                                userKeysDao.addAllRemoteKeys(keys)

                                MediatorResult.Success(
                                    endOfPaginationReached = isEnd
                                )


                            }
                        }

                        else -> MediatorResult.Error(Exception())
                    }
                }
            }else{
                MediatorResult.Success(false)
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                userKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { user ->
                userKeysDao.getRemoteKeys(id = user.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { quote ->
                userKeysDao.getRemoteKeys(id = quote.id)
            }
    }

}