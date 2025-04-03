package com.example.tbcacademyhomework.presentation.screen.register

data class RegisterScreenState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLoading: Boolean = false,
) {
    val isUserValid = isEmailValid && isPasswordValid && password == repeatPassword
}