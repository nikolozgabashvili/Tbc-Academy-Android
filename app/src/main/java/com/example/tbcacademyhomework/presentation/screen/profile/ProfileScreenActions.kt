package com.example.tbcacademyhomework.presentation.screen.profile

sealed interface ProfileScreenAction {
    data object OnLogout : ProfileScreenAction
}