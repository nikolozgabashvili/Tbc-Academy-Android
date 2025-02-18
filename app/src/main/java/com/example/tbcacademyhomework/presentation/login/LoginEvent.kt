package com.example.tbcacademyhomework.presentation.login

import com.example.tbcacademyhomework.presentation.utils.GenericString


sealed interface LoginEvent {
    data object Success : LoginEvent
    data class Error(val error: GenericString) : LoginEvent
}