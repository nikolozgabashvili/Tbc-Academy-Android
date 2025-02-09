package com.example.tbcacademyhomework.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.local.UserPrefsDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val userPrefsDataSource: UserPrefsDataSource
) : ViewModel() {

    private val eventChannel = Channel<Unit>()
    val event = eventChannel.receiveAsFlow()


    fun logOutUser() {
        viewModelScope.launch {
            userPrefsDataSource.clearData()
            eventChannel.send(Unit)
        }

    }

}