package com.example.tbcacademyhomework.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.data.local.database.AppDatabase
import com.example.tbcacademyhomework.domain.local.datastore.UserPrefsDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val userPrefsDataSource: UserPrefsDataSource,
    private val appDatabase: AppDatabase
) : ViewModel() {

    private val eventChannel = Channel<Unit>()
    val event = eventChannel.receiveAsFlow()


    fun logOutUser() {
        viewModelScope.launch {
            userPrefsDataSource.clearData()
            appDatabase.dao.clearAll()
            appDatabase.remoteKeysDao.deleteAllRemoteKeys()
            eventChannel.send(Unit)
        }

    }

}