package com.example.tbcacademyhomework.presentation.screen.login

import com.example.tbcacademyhomework.presentation.utils.GenericStrings


sealed interface LoginEvent {
    data object Success : LoginEvent
    data object NavigateToRegister : LoginEvent
    data class Error(val error: GenericStrings) : LoginEvent
}