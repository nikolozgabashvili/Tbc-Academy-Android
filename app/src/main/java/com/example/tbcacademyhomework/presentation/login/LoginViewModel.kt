package com.example.tbcacademyhomework.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.core.repository.UserPrefsRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.auth.validation.UserDataValidator
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPrefsRepository: UserPrefsRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<LoginEvent>()
    val event = eventsChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            userPrefsRepository.getShouldRemember().let { shouldRemember ->
                if (shouldRemember != true) {
                    userPrefsRepository.clearData()
                } else {
                    if (userPrefsRepository.getUserEmail() != null) {
                        eventsChannel.send(LoginEvent.Success)
                    }
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

                userPrefsRepository.saveToken(resource.data.token)
                userPrefsRepository.savEmail(email)
                userPrefsRepository.saveShouldRemember(_state.value.checkboxChecked)
                eventsChannel.send(LoginEvent.Success)
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
            val isUserValid =
                userDataValidator.isUserValid(email, password)
            update { it.copy(isUserValid = isUserValid) }

        }
    }

}