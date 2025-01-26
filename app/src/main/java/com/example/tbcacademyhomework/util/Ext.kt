package com.example.tbcacademyhomework.util

import com.example.tbcacademyhomework.home.User
import com.example.tbcacademyhomework.network.model.UserDto

fun UserDto.toUser(): User? {
    return id?.let {id ->
        User(
            id = id,
            name = firstName,
            lastName = lastName,
            email = email,
            icon = avatar

        )
    }
}