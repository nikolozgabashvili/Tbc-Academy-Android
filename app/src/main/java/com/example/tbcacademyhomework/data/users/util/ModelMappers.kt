package com.example.tbcacademyhomework.data.users.util

import com.example.tbcacademyhomework.data.users.database.user.UserEntity
import com.example.tbcacademyhomework.data.users.models.UserDto
import com.example.tbcacademyhomework.data.users.models.UsersListDto
import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.domain.users.models.UsersList

fun User.toUserEntity(page: Int): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar,
        page = page
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

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}