package com.example.tbcacademyhomework.data.auth.util

import com.example.tbcacademyhomework.data.auth.models.AuthResponseDto
import com.example.tbcacademyhomework.data.auth.models.AuthUserRequest
import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser

fun AuthUser.toAuthUserRequest(): AuthUserRequest {
    return AuthUserRequest(
        email = email,
        password = password
    )
}

fun AuthResponseDto.toDomain(): AuthResponse {
    return AuthResponse(
        token = token,
        id = id
    )
}