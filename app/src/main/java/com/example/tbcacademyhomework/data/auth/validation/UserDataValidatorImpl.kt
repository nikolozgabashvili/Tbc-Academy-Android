package com.example.tbcacademyhomework.data.auth.validation

import android.util.Patterns
import com.example.tbcacademyhomework.domain.auth.validation.UserDataValidator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataValidatorImpl @Inject constructor() : UserDataValidator {

    override fun isUserValid(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotBlank()
    }
}