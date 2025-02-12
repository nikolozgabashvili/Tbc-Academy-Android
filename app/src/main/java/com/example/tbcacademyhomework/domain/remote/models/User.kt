package com.example.tbcacademyhomework.domain.remote.models

data class User(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)
