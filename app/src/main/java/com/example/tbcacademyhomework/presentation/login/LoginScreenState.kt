package com.example.tbcacademyhomework.presentation.login

data class LoginScreenState(
    val isLoading: Boolean = false,
    val emailText: String = "",
    val passwordText: String = "",
    val remember: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
) {
    val isUserValid = isEmailValid && isPasswordValid
}