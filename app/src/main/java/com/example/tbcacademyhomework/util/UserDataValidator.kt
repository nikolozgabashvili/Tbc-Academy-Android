package com.example.tbcacademyhomework.util

import android.util.Patterns

class UserDataValidator {

    fun validateEmail(mail: String): ValidationError? {
        return if (mail.isEmpty()) ValidationError.EMPTY_FIELD
        else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) ValidationError.INVALID_EMAIL
        else null
    }

    fun validatePassword(pass: String): ValidationError? {
        return if (pass.isEmpty()) ValidationError.EMPTY_FIELD
        else null
    }

}