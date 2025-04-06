package com.example.tbcacademyhomework.presentation.screen.register

import com.example.tbcacademyhomework.presentation.utils.GenericStrings

sealed interface RegisterEvent {
    data class Success(val params:UserParams) : RegisterEvent
    data class Error(val error: GenericStrings) : RegisterEvent
    data object NavigateBack : RegisterEvent
}