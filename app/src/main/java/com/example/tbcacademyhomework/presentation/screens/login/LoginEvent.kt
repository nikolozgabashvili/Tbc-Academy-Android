package com.example.tbcacademyhomework.presentation.screens.login

import com.example.tbcacademyhomework.presentation.utils.GenericString


sealed interface LoginEvent {
    data class Success(val email: String) : LoginEvent
    data class Error(val error: GenericString) : LoginEvent
}