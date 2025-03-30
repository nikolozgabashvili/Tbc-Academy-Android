package com.example.tbcacademyhomework.presentation.screen.account

import com.example.tbcacademyhomework.presentation.common.util.GenericString

sealed interface AccountScreenEvent {
    data class Error(val message: GenericString) : AccountScreenEvent
}