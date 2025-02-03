package com.example.tbcacademyhomework.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    val status:Boolean,
    val permissions :List<String?>,
    val users :List<UserDto>
)
