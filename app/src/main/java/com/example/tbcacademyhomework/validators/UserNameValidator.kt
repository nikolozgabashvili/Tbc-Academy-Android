package com.example.tbcacademyhomework.validators

import com.example.tbcacademyhomework.R

class UserNameValidator : Validator {
    override fun validate(input: String?): ValidationResult {
        return when {
            input.isNullOrBlank() -> ValidationResult(
                errorMessage = R.string.empty_field_error,
                isError = true
            )

            input.length < 10 -> ValidationResult(
                errorMessage = R.string.username_field_length_error,
                isError = true
            )

            else -> ValidationResult()
        }
    }
}