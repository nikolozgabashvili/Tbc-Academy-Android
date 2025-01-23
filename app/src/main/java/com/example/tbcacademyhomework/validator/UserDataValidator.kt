package com.example.tbcacademyhomework.validator

import android.util.Patterns

object UserDataValidator {
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isFieldValid(input: String) = input.isNotBlank()

}