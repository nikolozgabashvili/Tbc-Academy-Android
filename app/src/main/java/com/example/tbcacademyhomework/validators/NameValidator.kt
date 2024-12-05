package com.example.tbcacademyhomework.validators

import com.example.tbcacademyhomework.R

class NameValidator : Validator {
    override fun validate(input: String?): ValidationResult {

        return when {
            input.isNullOrBlank() -> ValidationResult(
                isError = true,
                errorMessage = R.string.empty_field_error
            )

            input.any { it.isDigit() } -> ValidationResult(
                isError = true,
                errorMessage = R.string.only_letters_error
            )

            input.length < 2 -> ValidationResult(
                isError = true,
                errorMessage = R.string.field_length_error
            )

            else -> ValidationResult()
        }
    }
}


