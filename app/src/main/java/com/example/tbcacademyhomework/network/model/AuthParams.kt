package com.example.tbcacademyhomework.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthParams(
    val email: String,
    val password: String
)
