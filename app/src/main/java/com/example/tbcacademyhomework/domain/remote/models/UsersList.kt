package com.example.tbcacademyhomework.domain.remote.models

data class UsersList(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<User>
)