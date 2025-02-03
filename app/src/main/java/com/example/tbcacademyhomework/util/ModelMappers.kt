package com.example.tbcacademyhomework.util

import com.example.tbcacademyhomework.data.local.entity.UserEntity
import com.example.tbcacademyhomework.data.remote.model.UserDto
import com.example.tbcacademyhomework.presentation.model.UserUi

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        about = about,
        activationStatus = activationStatus
    )
}


fun UserEntity.toUserUi(): UserUi {
    return UserUi(
        id = id,
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        about = about,
        activationStatus = activationStatus.asStatus()
    )
}




