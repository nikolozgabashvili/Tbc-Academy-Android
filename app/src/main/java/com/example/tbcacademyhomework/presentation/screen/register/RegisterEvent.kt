package com.example.tbcacademyhomework.presentation.screen.register

import com.example.tbcacademyhomework.presentation.utils.GenericString

sealed interface RegisterEvent {
    data class Success(val params:UserParams) : RegisterEvent
    data class Error(val error: GenericString) : RegisterEvent
}