package com.example.tbcacademyhomework.presentation.screen.login

data class LoginScreenState(
    val isLoading: Boolean = false,
    val emailText: String = "",
    val passwordText: String = "",
    val remember: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val showScreen :Boolean = false
) {
    val isUserValid = isEmailValid && isPasswordValid
}