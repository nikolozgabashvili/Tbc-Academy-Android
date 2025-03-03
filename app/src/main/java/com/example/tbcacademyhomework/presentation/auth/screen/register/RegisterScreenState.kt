package com.example.tbcacademyhomework.presentation.auth.screen.register

import com.example.tbcacademyhomework.domain.core.validation.PasswordValidationState

data class RegisterScreenState(
    val loading: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val password: String = "",
) {
    val showValidation: Boolean
        get() = password.isNotEmpty() && !passwordValidationState.isValid
}
