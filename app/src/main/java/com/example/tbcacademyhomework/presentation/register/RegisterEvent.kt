package com.example.tbcacademyhomework.presentation.register

import com.example.tbcacademyhomework.presentation.utils.GenericString

sealed interface RegisterEvent {
    data class Success(val params: RegisterParams) : RegisterEvent
    data class Error(val error: GenericString) : RegisterEvent
}