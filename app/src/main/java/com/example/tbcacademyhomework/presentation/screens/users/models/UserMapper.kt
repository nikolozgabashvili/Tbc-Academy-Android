package com.example.tbcacademyhomework.presentation.screens.users.models

import com.example.tbcacademyhomework.domain.users.models.User

fun User.toUserUi(): UserUi {
    return UserUi(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}