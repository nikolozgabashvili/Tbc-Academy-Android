package com.example.tbcacademyhomework.presentation.auth.screen.register

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.auth.usecase.RegisterUseCase
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isLoading
import com.example.tbcacademyhomework.domain.core.validation.usecase.IsEmailValidUseCase
import com.example.tbcacademyhomework.domain.core.validation.usecase.ValidatePasswordUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterScreenEvents>()
    val events = eventChannel.receiveAsFlow()

    fun onPasswordChanged(password: String) {

        _state.update {
            it.copy(
                password = password,
                passwordValidationState = validatePasswordUseCase(password)
            )
        }
    }

    fun register(
        email: String,
        password: String,
        repeatPassword: String
    ) {
        val isEmailValid = isEmailValidUseCase(email)
        val isPasswordValid = validatePasswordUseCase(password).isValid
        val isRepeatPasswordValid = password == repeatPassword
        launchCoroutineScope {
            if (isEmailValid && isPasswordValid && isRepeatPasswordValid) {
                registerUseCase(email, password).collect { resource ->
                    _state.update {
                        it.copy(loading = resource.isLoading())
                    }
                    when (resource) {
                        is Resource.Success -> {
                            eventChannel.send(RegisterScreenEvents.Success)
                        }

                        is Resource.Error -> {
                            eventChannel.send(RegisterScreenEvents.Error(resource.error))
                        }

                        Resource.Loading -> Unit
                    }

                }
            } else {
                eventChannel.send(
                    RegisterScreenEvents.InvalidInputs(
                        emailValid = isEmailValid,
                        passwordValid = isPasswordValid,
                        repeatPasswordValid = isRepeatPasswordValid
                    )
                )
            }


        }
    }


}