package com.example.tbcacademyhomework.presentation.auth.screen.login

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.auth.usecase.GetFirebaseAuthStateUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.LoginUseCase
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isLoading
import com.example.tbcacademyhomework.domain.core.validation.usecase.IsEmailValidUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val loginUseCase: LoginUseCase,
    private val getFirebaseAuthStateUseCase: GetFirebaseAuthStateUseCase

) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()


    private val eventChannel = Channel<LoginScreenEvents>()
    val events = eventChannel.receiveAsFlow()

    init {
        launchCoroutineScope {
            getFirebaseAuthStateUseCase().collect{loggedIn->
                if (loggedIn){
                    eventChannel.send(LoginScreenEvents.NavigateHome)
                }

            }
        }
    }


    fun onLoginClicked(email: String, password: String) {
        val isEmailValid = isEmailValidUseCase(email)
        val isPasswordValid = password.isNotEmpty()
        launchCoroutineScope {
            if (!isEmailValid || !isPasswordValid) {
                eventChannel.send(
                    LoginScreenEvents.InvalidInputs(
                        passwordValid = isPasswordValid,
                        emailValid = isEmailValid
                    )
                )
            } else {
                loginUseCase(email, password).collect { resource ->
                    _state.update { it.copy(loading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> {
                            eventChannel.send(LoginScreenEvents.NavigateHome)
                        }

                        is Resource.Error -> {
                            eventChannel.send(LoginScreenEvents.Error(resource.error))
                        }

                        Resource.Loading -> Unit
                    }
                }
            }

        }
    }


}