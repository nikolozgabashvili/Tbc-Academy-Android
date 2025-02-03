package com.example.tbcacademyhomework.presentation.model

import com.example.tbcacademyhomework.util.ActivationStatus


data class UserUi(
    val id: Int,
    val avatar: String?,
    val firstName: String,
    val lastName: String,
    val about: String?,
    val activationStatus: ActivationStatus
)
