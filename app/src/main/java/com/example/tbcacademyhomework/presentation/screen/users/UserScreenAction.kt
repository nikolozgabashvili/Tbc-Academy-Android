package com.example.tbcacademyhomework.presentation.screen.users

sealed interface UserScreenAction {
    data object OnFetchRequest : UserScreenAction
}