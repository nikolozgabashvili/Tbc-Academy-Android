package com.example.tbcacademyhomework.domain.auth.screen.login

import androidx.lifecycle.ViewModel
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

    ) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()


    private val eventChannel = Channel<LoginScreenEvents>()
    val events = eventChannel.receiveAsFlow()

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email, isEmailValid = isEmailValidUseCase(email)) }
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(
                password = password,
            )
        }

    }

    fun onLoginClicked() {
        val isEmailValid = _state.value.isEmailValid
        val isPasswordValid = _state.value.isPasswordValid
        launchCoroutineScope {
            if (!isEmailValid || !isPasswordValid) {
                eventChannel.send(
                    LoginScreenEvents.InvalidInputs(
                        passwordValid = isPasswordValid,
                        emailValid = isEmailValid
                    )
                )
            }

        }
    }


}