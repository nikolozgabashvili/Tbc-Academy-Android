package com.example.tbcacademyhomework.presentation.login

sealed interface LoginScreenAction {
    data class OnInputChanged(val email: String, val password: String) : LoginScreenAction
    data class OnLogin(val email: String, val password: String, val remember: Boolean) :
        LoginScreenAction

}