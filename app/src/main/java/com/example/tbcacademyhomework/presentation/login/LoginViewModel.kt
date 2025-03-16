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
import com.example.tbcacademyhomework.domain.datastore.usecase.SetValueUseCase
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
    private val saveValueUseCase: SetValueUseCase,
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
            is LoginScreenAction.OnInputChanged -> {
                validateUser(action.email, action.password)
            }

            is LoginScreenAction.OnLogin -> {
                loginUser(action.email, action.password, action.remember)


            }
        }

    }


    private suspend fun handleResource(
        resource: Resource<LoginResponseDomain, DataError>,
        email: String,
        remember: Boolean
    ) {

        updateState { copy(isLoading = resource.isLoading()) }
        when (resource) {
            is Resource.Success -> {
                saveValueUseCase(DatastorePreferenceKeys.TOKEN, resource.data.token)
                saveValueUseCase(DatastorePreferenceKeys.EMAIL, email)
                saveValueUseCase(
                    DatastorePreferenceKeys.SHOULD_REMEMBER, remember
                )
                sendEvent(LoginEvent.Success)
            }

            is Resource.Error -> {
                sendEvent(LoginEvent.Error(resource.error.toGenericString()))
            }

            Resource.Loading -> Unit
        }

    }

    private fun validateUser(email: String, password: String) {
        val isUserValid = validateEmailUseCase(email) && validatePasswordUseCase(password)
        updateState { copy(isUserValid = isUserValid) }
    }


    private fun loginUser(email: String, password: String, remember: Boolean) {
        viewModelScope.launch {
            loginUseCase(AuthUser(email, password)).collect {
                handleResource(it, email, remember)
            }
        }

    }

}