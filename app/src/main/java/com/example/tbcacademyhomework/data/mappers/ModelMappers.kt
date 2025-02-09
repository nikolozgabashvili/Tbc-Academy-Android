package com.example.tbcacademyhomework.data.mappers

import com.example.tbcacademyhomework.data.auth.models.AuthResponseDto
import com.example.tbcacademyhomework.data.auth.models.AuthUserRequest
import com.example.tbcacademyhomework.data.users.models.UserDto
import com.example.tbcacademyhomework.data.users.models.UsersListDto
import com.example.tbcacademyhomework.domain.auth.models.AuthResponse
import com.example.tbcacademyhomework.domain.auth.models.AuthUser
import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.domain.users.models.UsersList

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

fun UsersListDto.toDomain(): UsersList {
    return UsersList(
        page = page,
        perPage = perPage,
        total = total,
        totalPages = totalPages,
        data = data.map { it.toDomain() }
    )
}

fun UserDto.toDomain(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}