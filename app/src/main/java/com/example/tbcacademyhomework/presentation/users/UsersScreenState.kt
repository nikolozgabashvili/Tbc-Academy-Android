package com.example.tbcacademyhomework.presentation.users

import com.example.tbcacademyhomework.presentation.users.models.UserUi

data class UsersScreenState(
    val loading: Boolean = false,
    val users: List<UserUi> = emptyList(),
    val pagingFinished :Boolean = false,
)
