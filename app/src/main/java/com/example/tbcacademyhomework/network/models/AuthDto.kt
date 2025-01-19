package com.example.tbcacademyhomework.network.models

import com.example.tbcacademyhomework.screens.login.model.LoginResponse
import com.example.tbcacademyhomework.screens.register.model.RegisterResponse
import kotlinx.serialization.Serializable

@Serializable
data class AuthDto(val id: Int?, val token: String?)


fun AuthDto.toLoginResponse(): LoginResponse? {
    return token?.let {
        LoginResponse(it)
    }
}

fun AuthDto.toRegisterResponse(): RegisterResponse? {
    return token?.let { token ->
        id?.let { id ->
            RegisterResponse(id, token)
        }
    }
}

