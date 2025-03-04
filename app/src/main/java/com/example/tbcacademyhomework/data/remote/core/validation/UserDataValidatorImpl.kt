package com.example.tbcacademyhomework.data.remote.core.validation

import android.util.Patterns
import com.example.tbcacademyhomework.domain.core.validation.PasswordValidationState
import com.example.tbcacademyhomework.domain.core.validation.UserDataValidator
import javax.inject.Inject

class UserDataValidatorImpl @Inject constructor(
) : UserDataValidator {

    override fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun validatePassword(password: String): PasswordValidationState {
        val hadMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        return PasswordValidationState(
            hasMinLength = hadMinLength,
            hasNumber = hasDigit,
            hasLowerCase = hasLowerCase,
            hasUpperCase = hasUpperCase,
            minLength = MIN_PASSWORD_LENGTH
        )
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
    }
}