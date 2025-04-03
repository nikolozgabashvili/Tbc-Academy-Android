package com.example.tbcacademyhomework.presentation.screen.users

import com.example.tbcacademyhomework.presentation.utils.GenericString

sealed interface UserScreenEvent {
    data class Error(val error: GenericString) : UserScreenEvent
}