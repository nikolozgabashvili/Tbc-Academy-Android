package com.example.tbcacademyhomework.network.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthParams(
    val email: String,
    val password: String
)
