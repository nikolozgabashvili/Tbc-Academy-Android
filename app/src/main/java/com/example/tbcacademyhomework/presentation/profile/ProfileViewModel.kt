package com.example.tbcacademyhomework.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.profile.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val eventChannel = Channel<Unit>()
    val event = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getUserEmail().collect { email ->
                _state.update { it.copy(email = email) }
            }

        }

    }


    fun logOutUser() {
        viewModelScope.launch {
            profileRepository.clearUser()
            eventChannel.send(Unit)
        }

    }

}