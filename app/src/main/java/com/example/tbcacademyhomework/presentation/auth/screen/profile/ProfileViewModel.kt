package com.example.tbcacademyhomework.presentation.auth.screen.profile

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.auth.usecase.GetFirebaseAuthStateUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.GetUserEmailUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.SignOutUseCase
import com.example.tbcacademyhomework.domain.meal.usecase.ClearFavouritesUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserEmailUseCase: GetUserEmailUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getFirebaseAuthStateUseCase: GetFirebaseAuthStateUseCase,
    private val clearFavouritesUseCase: ClearFavouritesUseCase
) : ViewModel() {

    private val eventChannel = Channel<ProfileScreenEvent>()
    val events = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        getAuthState()
        getEmail()


    }

    private fun getEmail() {
        launchCoroutineScope {
            _state.update {
                it.copy(email = getUserEmailUseCase())
            }
        }
    }

    private fun getAuthState() {
        launchCoroutineScope {
            getFirebaseAuthStateUseCase().collect { authorized ->
                if (!authorized) {
                    clearFavouritesUseCase()
                    eventChannel.send(ProfileScreenEvent.SignOut)

                }
            }
        }
    }

    fun signOut() {
        launchCoroutineScope {
            signOutUseCase().collect()
        }
    }

}