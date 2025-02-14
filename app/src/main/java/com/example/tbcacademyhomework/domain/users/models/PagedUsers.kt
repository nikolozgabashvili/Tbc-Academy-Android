package com.example.tbcacademyhomework.domain.users.models

data class PagedUsers(
    val endReached: Boolean,
    val users: List<User>
)