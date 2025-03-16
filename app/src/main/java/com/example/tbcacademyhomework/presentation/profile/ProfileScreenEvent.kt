package com.example.tbcacademyhomework.presentation.profile

sealed interface ProfileScreenEvent {
    data object Success:ProfileScreenEvent
}