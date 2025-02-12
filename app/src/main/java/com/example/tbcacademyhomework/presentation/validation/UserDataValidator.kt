package com.example.tbcacademyhomework.presentation.validation

import android.util.Patterns
import com.example.tbcacademyhomework.domain.validator.Validator


class EmailValidator : Validator {
    override fun isValid(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }
}

class InputValidator : Validator {
    override fun isValid(input: String): Boolean {
        return input.isNotBlank()
    }
}
