package com.example.tbcacademyhomework.presentation.screen.register

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


    private fun registerUser() {
        viewModelScope.launch {
            registerUseCase(AuthUser(email = state.email, password = state.email))
                .collect { resource ->
                    handleResource(resource)
                }
        }

    }


    private fun handleResource(
        resource: Resource<RegisterResponseDomain, DataError>
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
                            UserParams(
                                email = state.email,
                                password = state.password
                            )
                        )
                    )
                }

                Resource.Loading -> Unit
            }

        }
    }


    override fun onAction(action: RegisterScreenAction) {

        when (action) {

            is RegisterScreenAction.EnterEmail -> enterEmail(action.email)
            is RegisterScreenAction.EnterPassword -> enterPassword(action.password)
            is RegisterScreenAction.EnterRepeatPassword -> enterRepeatPassword(action.password)
            RegisterScreenAction.Register -> registerUser()
            RegisterScreenAction.NavigateBack -> navigateBack()
        }

    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendEvent(RegisterEvent.NavigateBack)
        }
    }

    private fun enterEmail(email: String) {
        val isValid = validateEmailUseCase(email)
        updateState { copy(email = email, isEmailValid = isValid) }
    }

    private fun enterPassword(password: String) {
        val isValid = validatePasswordUseCase(password)
        updateState { copy(password = password, isPasswordValid = isValid) }
    }

    private fun enterRepeatPassword(password: String) {
        updateState { copy(repeatPassword = password) }
    }


}