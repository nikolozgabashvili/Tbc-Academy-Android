package com.example.tbcacademyhomework.data.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDTO(
    val id: Int?,
    val token: String
)