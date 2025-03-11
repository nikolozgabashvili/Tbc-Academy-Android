package com.example.tbcacademyhomework.data.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val token: String
)
