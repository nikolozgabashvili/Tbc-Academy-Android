package com.example.tbcacademyhomework.domain.auth.screen.login

sealed interface LoginScreenEvents {
    data object LoginSuccess : LoginScreenEvents
    data class InvalidInputs(val passwordValid: Boolean, val emailValid: Boolean) :
        LoginScreenEvents

    data object Error : LoginScreenEvents
}