package com.example.tbcacademyhomework.presentation.screen.profile

sealed interface ProfileScreenEvent {
    data object Success: ProfileScreenEvent
}