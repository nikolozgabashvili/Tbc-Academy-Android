package com.example.tbcacademyhomework.presentation.users

import com.example.tbcacademyhomework.presentation.utils.GenericString

sealed interface UserScreenEvent {
    data class Error(val error: GenericString):UserScreenEvent
}