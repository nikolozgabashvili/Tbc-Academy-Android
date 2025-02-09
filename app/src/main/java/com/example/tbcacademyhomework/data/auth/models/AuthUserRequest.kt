package com.example.tbcacademyhomework.data.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthUserRequest(
    val email: String,
    val password: String
)
