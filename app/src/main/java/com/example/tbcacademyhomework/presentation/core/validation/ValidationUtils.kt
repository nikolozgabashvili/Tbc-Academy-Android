package com.example.tbcacademyhomework.presentation.core.validation

import androidx.fragment.app.Fragment
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.core.validation.PasswordValidationState

private fun Fragment.getValidationItems(state: PasswordValidationState): List<ValidationItem> {
    return listOf(
        ValidationItem(
            message = getString(
                R.string.validation_password_length_message,
                state.minLength.toString()
            ),
            isValid = state.hasMinLength
        ),
        ValidationItem(
            message = getString(R.string.validation_uppercase_letter),
            isValid = state.hasUpperCase
        ),
        ValidationItem(
            message = getString(R.string.validation_lowercase_letter),
            isValid = state.hasLowerCase
        ),
        ValidationItem(
            message = getString(R.string.validation_number),
            isValid = state.hasNumber
        )
    )

}