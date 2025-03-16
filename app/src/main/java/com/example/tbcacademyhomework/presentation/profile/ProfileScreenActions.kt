package com.example.tbcacademyhomework.presentation.profile

sealed interface ProfileScreenAction {
    data object OnLogout : ProfileScreenAction
}