package com.example.tbcacademyhomework.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.auth.usecase.LoginUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidateEmailUseCase
import com.example.tbcacademyhomework.domain.datastore.DatastorePreferenceKeys
import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCase
import com.example.tbcacademyhomework.domain.datastore.usecase.SetValueUseCase
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveValueUseCase: SetValueUseCase,
    private val getValueUseCase: GetValueUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val clearDataUseCase: ClearDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<LoginEvent>()
    val event = eventsChannel.receiveAsFlow()

    init {
        viewModelScope.launch {

            getValueUseCase(DatastorePreferenceKeys.SHOULD_REMEMBER, false).first()
                .let { shouldRemember ->
                    if (shouldRemember != true) {
                        clearDataUseCase()
                    } else {
                        if (getValueUseCase(DatastorePreferenceKeys.EMAIL, null).first() != null) {
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
            loginUseCase(AuthUser(email, password)).collect { resource ->
                _state.update { it.copy(authResource = resource) }
                handleResource(resource)
            }
        }
    }

    private suspend fun handleResource(resource: Resource<LoginResponseDomain, DataError>) {
        when (resource) {
            is Resource.Success -> {
                val email = _state.value.userEmail

                saveValueUseCase(DatastorePreferenceKeys.TOKEN, resource.data.token)
                saveValueUseCase(DatastorePreferenceKeys.EMAIL, email)
                saveValueUseCase(
                    DatastorePreferenceKeys.SHOULD_REMEMBER,
                    _state.value.checkboxChecked
                )
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
            val isUserValid = validateEmailUseCase(email) && password.isNotBlank()
            update { it.copy(isUserValid = isUserValid) }

        }
    }

}