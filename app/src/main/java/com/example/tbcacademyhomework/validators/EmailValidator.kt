package com.example.tbcacademyhomework.validators

import android.util.Patterns
import com.example.tbcacademyhomework.R

class EmailValidator :Validator{
    override fun validate(input: String?): ValidationResult {
        return when {
            input.isNullOrBlank() -> ValidationResult(
                isError = true,
                errorMessage = R.string.empty_field_error
            )

            !Patterns.EMAIL_ADDRESS.matcher(input).matches() -> ValidationResult(
                isError = true,
                errorMessage = R.string.email_validation_error
            )

            else -> ValidationResult()
        }

    }
}