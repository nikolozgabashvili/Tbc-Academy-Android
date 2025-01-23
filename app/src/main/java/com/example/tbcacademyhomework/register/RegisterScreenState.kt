package com.example.tbcacademyhomework.register

import com.example.tbcacademyhomework.validator.UserDataValidator

data class RegisterScreenState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
) {
    val isUserValid: Boolean
        get() {
            return with(UserDataValidator) {
                isValidEmail(email) && isFieldValid(password) && password == repeatPassword
            }
        }
}
