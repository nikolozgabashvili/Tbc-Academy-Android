package com.example.tbcacademyhomework.presentation.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tbcacademyhomework.data.local.database.AppDatabase
import com.example.tbcacademyhomework.data.mappers.toUserUi
import com.example.tbcacademyhomework.data.paging.UsersPagingSource
import com.example.tbcacademyhomework.domain.connectivity_observer.ConnectivityObserver
import com.example.tbcacademyhomework.domain.remote.repository.UsersRemoteSource
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class UsersViewModel(
    usersRemoteSource: UsersRemoteSource,
    connectivityObserver: ConnectivityObserver,
    private val appDatabase: AppDatabase
) : ViewModel() {


    private val pager = Pager(
        config = PagingConfig(pageSize = 6, enablePlaceholders = false, prefetchDistance = 2),
        remoteMediator = UsersPagingSource(appDatabase, usersRemoteSource, connectivityObserver)
    ) {
        appDatabase.dao.userPagingSource()
    }

    val usersFlow =
        pager.flow.map { user -> user.map { it.toUserUi() } }.cachedIn(viewModelScope)


}