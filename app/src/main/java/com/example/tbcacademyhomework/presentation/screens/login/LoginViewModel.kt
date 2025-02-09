package com.example.tbcacademyhomework.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.local.UserPrefsDataSource
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import com.example.tbcacademyhomework.presentation.validation.EmailValidator
import com.example.tbcacademyhomework.presentation.validation.InputValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userPrefsDataSource: UserPrefsDataSource,
    private val emailValidator: EmailValidator,
    private val passwordValidator: InputValidator
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<LoginEvent>()
    val event = eventsChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            userPrefsDataSource.getUserEmail().collectLatest {
                if (it != null) {
                    eventsChannel.send(LoginEvent.Success(it))
                }
            }
        }
    }


    fun loginUser() {
        viewModelScope.launch {
            val email = _state.value.userEmail
            val password = _state.value.userPassword
            authRepository.loginUser(AuthUser(email, password)).collect { resource ->
                _state.update { it.copy(authResource = resource) }
                handleResource(resource)
            }
        }
    }

    private suspend fun handleResource(resource: Resource<AuthResponse, DataError>) {
        when (resource) {
            is Resource.Success -> {
                val email = _state.value.userEmail
                if (_state.value.checkboxChecked) {

                    userPrefsDataSource.saveToken(resource.data.token)
                    userPrefsDataSource.savEmail(email)
                } else {
                    eventsChannel.send(LoginEvent.Success(email = email))
                }
            }

            is Resource.Error -> {
                eventsChannel.send(LoginEvent.Error(resource.error.toGenericString()))
            }

            Resource.Loading -> Unit
        }

    }

    fun updateCheckboxState(isChecked: Boolean) {
        _state.update { it.copy(checkboxChecked = isChecked) }
    }

    fun updateEmail(email: String) {
        _state.update { it.copy(userEmail = email) }
        validateUser()
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(userPassword = password) }
        validateUser()
    }

    private fun validateUser() {
        with(_state) {
            val password = value.userPassword
            val email = value.userEmail
            val isUserValid = emailValidator.isValid(email) && passwordValidator.isValid(password)
            update { it.copy(isUserValid = isUserValid) }

        }
    }

}