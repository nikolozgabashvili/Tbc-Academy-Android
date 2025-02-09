package com.example.tbcacademyhomework.data.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val id: Int?,
    val token: String
)