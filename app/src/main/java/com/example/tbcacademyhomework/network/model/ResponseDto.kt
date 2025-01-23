package com.example.tbcacademyhomework.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val id: Int?,
    val token: String?
)