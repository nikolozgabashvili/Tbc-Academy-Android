package com.example.tbcacademyhomework.presentation.util


sealed interface ScreenEvent {
    data object Success : ScreenEvent
    data class Error(val error: GenericString) : ScreenEvent
}