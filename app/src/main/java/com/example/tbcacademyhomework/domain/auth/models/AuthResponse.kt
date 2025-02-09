package com.example.tbcacademyhomework.domain.auth.models

data class AuthResponse(
    val token: String,
    val id: Int? = null
)
