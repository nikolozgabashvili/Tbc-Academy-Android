package com.example.tbcacademyhomework.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.validator.Validator
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val emailValidator: Validator,
    private val passwordValidator: Validator
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterEvent>()
    val event = eventChannel.receiveAsFlow()

    fun registerUser() {
        viewModelScope.launch {
            val email = _state.value.email
            val password = _state.value.password
            authRepository.registerUser(AuthUser(email = email, password = password))
                .collect { resource ->
                    _state.update { it.copy(registerResource = resource) }
                    handleResource(resource)

                }
        }

    }


    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
        validateUser()
    }


    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
        validateUser()
    }

    fun updateRepeatPassword(password: String) {
        _state.update { it.copy(repeatPassword = password) }
        validateUser()

    }

    private fun handleResource(resource: Resource<AuthResponse, DataError>) {
        viewModelScope.launch {
            when (resource) {
                is Resource.Error -> {
                    eventChannel.send(RegisterEvent.Error(resource.error.toGenericString()))
                }

                is Resource.Success -> {
                    val email = _state.value.email
                    val password = _state.value.password
                    eventChannel.send(
                        RegisterEvent.Success(
                            RegisterParams(
                                email = email,
                                password = password
                            )
                        )
                    )
                }

                Resource.Loading -> Unit
            }

        }
    }


    private fun validateUser() {
        with(_state) {
            val password = value.password
            val email = value.email
            val repeatPassword = value.repeatPassword
            val isUserValid =
                emailValidator.isValid(email) && passwordValidator.isValid(password) && password == repeatPassword
            update { it.copy(isUserValid = isUserValid) }

        }

    }


}