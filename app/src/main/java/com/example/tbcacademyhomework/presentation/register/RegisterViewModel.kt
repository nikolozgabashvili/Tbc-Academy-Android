package com.example.tbcacademyhomework.presentation.register

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.RegisterResponseDomain
import com.example.tbcacademyhomework.domain.auth.usecase.RegisterUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidateEmailUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidatePasswordUseCase
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : BaseViewModel<RegisterScreenState, RegisterScreenAction, RegisterEvent>(RegisterScreenState()) {


    private fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            registerUseCase(AuthUser(email = email, password = password))
                .collect { resource ->
                    handleResource(resource, email, password)

                }
        }

    }


    private fun handleResource(
        resource: Resource<RegisterResponseDomain, DataError>,
        email: String,
        password: String
    ) {
        updateState { copy(isLoading = resource.isLoading()) }
        viewModelScope.launch {
            when (resource) {
                is Resource.Error -> {
                    sendEvent(RegisterEvent.Error(resource.error.toGenericString()))
                }

                is Resource.Success -> {
                    sendEvent(
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


    private fun validateUser(email: String, password: String, repeatPassword: String) {
        val isUserValid =
            validateEmailUseCase(email) && validatePasswordUseCase(password) && repeatPassword == password
        updateState { copy(isUserValid = isUserValid) }


    }

    override fun onAction(action: RegisterScreenAction) {

        when (action) {
            is RegisterScreenAction.OnUserInput -> {
                validateUser(
                    action.email,
                    action.password,
                    action.repeatPassword
                )
            }

            is RegisterScreenAction.OnRegister -> {
                registerUser(action.email, action.password)
            }

        }

    }


}