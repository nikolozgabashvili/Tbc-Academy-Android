package com.example.tbcacademyhomework.domain.auth.screen.login

data class LoginScreenState(
    val password: String = "",
    val email: String = "",
    val isEmailValid: Boolean = false,
) {
    val isPasswordValid: Boolean
        get() = password.isNotBlank()
}
