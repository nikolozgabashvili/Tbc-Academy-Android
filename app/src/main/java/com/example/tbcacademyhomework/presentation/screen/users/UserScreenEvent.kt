package com.example.tbcacademyhomework.presentation.screen.users

import com.example.tbcacademyhomework.presentation.utils.GenericStrings

sealed interface UserScreenEvent {
    data class Error(val error: GenericStrings) : UserScreenEvent
}