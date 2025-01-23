package com.example.tbcacademyhomework.login

import com.example.tbcacademyhomework.network.model.ResponseDto
import com.example.tbcacademyhomework.util.Resource
import com.example.tbcacademyhomework.validator.UserDataValidator

data class LoginScreenState(
    val userEmail: String = "",
    val userPassword: String = "",
    val checkboxChecked: Boolean = false,
    val authResource: Resource<ResponseDto>? = null

) {
    val isUserValid: Boolean
        get() {
            return with(UserDataValidator) {
                isFieldValid(userPassword) && isValidEmail(userEmail)
            }
        }
}