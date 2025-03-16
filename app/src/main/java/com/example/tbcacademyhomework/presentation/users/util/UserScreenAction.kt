package com.example.tbcacademyhomework.presentation.users.util

sealed interface UserScreenAction {
    data object OnFetchRequest : UserScreenAction
}