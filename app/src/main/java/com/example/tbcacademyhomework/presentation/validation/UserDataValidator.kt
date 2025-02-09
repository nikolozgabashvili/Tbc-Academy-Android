package com.example.tbcacademyhomework.presentation.validation

import android.util.Patterns

interface Validator {
    fun isValid(input: String): Boolean
}

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
