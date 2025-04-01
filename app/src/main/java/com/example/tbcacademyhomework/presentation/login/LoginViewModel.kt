package com.example.tbcacademyhomework.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.auth.usecase.LoginUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidateEmailUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidatePasswordUseCase
import com.example.tbcacademyhomework.domain.common.usecase.ClearDataUseCase
import com.example.tbcacademyhomework.domain.datastore.DatastorePreferenceKeys
import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCase
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getValueUseCase: GetValueUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val clearDataUseCase: ClearDataUseCase
) : BaseViewModel<LoginScreenState, LoginScreenAction, LoginEvent>(LoginScreenState()) {


    init {
        viewModelScope.launch {
            getValueUseCase(DatastorePreferenceKeys.SHOULD_REMEMBER, false).first()
                .let { shouldRemember ->
                    if (shouldRemember == true) {
                        if (getValueUseCase(DatastorePreferenceKeys.EMAIL, null).first() != null) {
                            sendEvent(LoginEvent.Success)
                        }
                    } else {
                        clearDataUseCase()
                    }
                }
        }
    }

    override fun onAction(action: LoginScreenAction) {
        when (action) {
            is LoginScreenAction.OnEmailChanged -> setEmail(action.email)
            is LoginScreenAction.OnPasswordChanged -> setPassword(action.password)
            LoginScreenAction.OnRememberClicked -> updateState { copy(remember = !remember) }
            LoginScreenAction.OnLogin -> loginUser()
        }

    }

    private fun setPassword(password: String) {
        val isValidPassword = validatePasswordUseCase(password)
        updateState { copy(passwordText = password, isPasswordValid = isValidPassword) }
    }

    private fun setEmail(email: String) {
        val isValidEmail = validateEmailUseCase(email)
        updateState { copy(emailText = email, isEmailValid = isValidEmail) }
    }


    private suspend fun handleLoginResource(
        resource: Resource<LoginResponseDomain, DataError>,
    ) {

        updateState { copy(isLoading = resource.isLoading()) }
        when (resource) {
            is Resource.Success -> {
                sendEvent(LoginEvent.Success)
            }

            is Resource.Error -> {
                sendEvent(LoginEvent.Error(resource.error.toGenericString()))
            }

            Resource.Loading -> Unit
        }

    }

    private fun loginUser() {
        val email = state.value.emailText
        val password = state.value.passwordText
        val remember = state.value.remember
        viewModelScope.launch {
            loginUseCase(AuthUser(email, password), remember).collect {
                handleLoginResource(it)
            }
        }

    }

}