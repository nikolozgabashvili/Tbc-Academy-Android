package com.example.tbcacademyhomework.presentation.users.util

import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.presentation.users.models.UserUi

fun User.toUserUi(): UserUi {
    return UserUi(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}