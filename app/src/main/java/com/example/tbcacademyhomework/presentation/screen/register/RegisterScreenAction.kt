package com.example.tbcacademyhomework.presentation.screen.register

sealed interface RegisterScreenAction {
    data class EnterEmail(val email: String) : RegisterScreenAction
    data class EnterPassword(val password: String) : RegisterScreenAction
    data class EnterRepeatPassword(val password: String) : RegisterScreenAction

    data object Register : RegisterScreenAction
}