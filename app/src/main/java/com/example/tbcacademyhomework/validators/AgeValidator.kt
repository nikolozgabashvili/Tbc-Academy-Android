package com.example.tbcacademyhomework.validators

import com.example.tbcacademyhomework.R

class AgeValidator : Validator {
    override fun validate(input: String?): ValidationResult {
        return when {


            input.isNullOrBlank() -> ValidationResult(
                isError = true,
                errorMessage = R.string.empty_field_error
            )

            input.any { !it.isDigit() } -> ValidationResult(
                isError = true,
                errorMessage = R.string.only_numbers_error
            )

            input.toInt() <= 0 -> ValidationResult(
                isError = true,
                errorMessage = R.string.age_limit_error
            )

            else -> ValidationResult()
        }

    }
}