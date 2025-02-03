package com.example.tbcacademyhomework.presentation.user

import com.example.tbcacademyhomework.presentation.model.UserUi

data class UsersScreenState(
    val isFetching: Boolean = false,
    val users: List<UserUi> = emptyList(),
) {
    val showPlaceholder: Boolean
        get() = isFetching && users.isEmpty()
}
