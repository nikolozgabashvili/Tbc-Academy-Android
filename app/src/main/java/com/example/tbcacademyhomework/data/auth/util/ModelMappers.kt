package com.example.tbcacademyhomework.data.auth.util

import com.example.tbcacademyhomework.data.auth.models.AuthUserRequest
import com.example.tbcacademyhomework.data.auth.models.LoginResponseDTO
import com.example.tbcacademyhomework.data.auth.models.RegisterResponseDTO
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.auth.models.LoginResponseDomain
import com.example.tbcacademyhomework.domain.auth.models.RegisterResponseDomain

fun AuthUser.toAuthUserRequest(): AuthUserRequest {
    return AuthUserRequest(
        email = email,
        password = password
    )
}

fun RegisterResponseDTO.toDomain(): RegisterResponseDomain {
    return RegisterResponseDomain(
        token = token,
        id = id
    )
}

fun LoginResponseDTO.toDomain(): LoginResponseDomain {
    return LoginResponseDomain(token)
}