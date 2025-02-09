package com.example.tbcacademyhomework.presentation.screens.login

import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource

data class LoginScreenState(
    val userEmail: String = "",
    val userPassword: String = "",
    val checkboxChecked: Boolean = false,
    val authResource: Resource<AuthResponse, DataError>? = null,
    val isUserValid: Boolean = false
)