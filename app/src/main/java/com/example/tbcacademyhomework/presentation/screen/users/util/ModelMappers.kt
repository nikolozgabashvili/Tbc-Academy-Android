package com.example.tbcacademyhomework.presentation.screen.users.util

import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.presentation.screen.users.models.UserUi

fun User.toUserUi(): UserUi {
    return UserUi(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}