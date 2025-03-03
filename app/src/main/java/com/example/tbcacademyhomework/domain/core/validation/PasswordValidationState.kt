package com.example.tbcacademyhomework.domain.core.validation

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasUpperCase: Boolean = false,
    val hasLowerCase: Boolean = false,
    val hasNumber: Boolean = false,
    val minLength: Int? = null
) {
    val isValid: Boolean
        get() = hasMinLength && hasUpperCase && hasLowerCase && hasNumber
}
