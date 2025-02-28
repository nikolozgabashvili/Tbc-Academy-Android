package com.example.tbcacademyhomework.presentation.map

import com.example.tbcacademyhomework.presentation.core.util.GenericString

sealed interface MapScreenEvent {
    data class Error(val error: GenericString) : MapScreenEvent

}