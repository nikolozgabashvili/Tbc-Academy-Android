package com.example.tbcacademyhomework.presentation.auth.screen.profile

sealed interface ProfileScreenEvent {
    data object SignOut : ProfileScreenEvent
}