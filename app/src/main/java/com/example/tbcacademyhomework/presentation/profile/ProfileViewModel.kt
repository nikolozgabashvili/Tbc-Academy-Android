package com.example.tbcacademyhomework.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.profile.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val eventChannel = Channel<Unit>()
    val event = eventChannel.receiveAsFlow()


    fun logOutUser() {
        viewModelScope.launch {
            profileRepository.clearUser()
            eventChannel.send(Unit)
        }

    }

}