package com.example.tbcacademyhomework.presentation.login

sealed interface LoginScreenAction {
    data class OnEmailChanged(val email: String) : LoginScreenAction
    data class OnPasswordChanged(val password: String) : LoginScreenAction
    data object OnRememberClicked : LoginScreenAction
    data object OnLogin : LoginScreenAction

}