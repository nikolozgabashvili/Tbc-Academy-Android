package com.example.tbcacademyhomework.presentation.screens.register

import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource

data class RegisterScreenState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isUserValid: Boolean = false,
    val registerResource: Resource<AuthResponse, DataError>? = null
)