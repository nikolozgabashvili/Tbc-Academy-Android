package com.example.tbcacademyhomework.presentation.register

sealed interface RegisterScreenAction {
    data class OnUserInput(val email: String, val password: String, val repeatPassword: String) :
        RegisterScreenAction

    data class OnRegister(val email: String, val password: String) : RegisterScreenAction
}