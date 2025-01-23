package com.example.tbcacademyhomework.login

import com.example.tbcacademyhomework.validator.UserDataValidator

data class LoginScreenState(
    val userEmail: String = "",
    val userPassword: String = "",
    val checkboxChecked: Boolean = false

) {
    val isUserValid: Boolean
        get() {
            return with(UserDataValidator) {
                isFieldValid(userPassword) && isValidEmail(userEmail)
            }
        }
}