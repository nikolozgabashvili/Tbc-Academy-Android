package com.example.tbcacademyhomework.presentation.screen.users

import com.example.tbcacademyhomework.presentation.screen.users.models.UserUi

data class UsersScreenState(
    val loading: Boolean = false,
    val users: List<UserUi> = emptyList(),
    val pagingFinished: Boolean = false,
)
