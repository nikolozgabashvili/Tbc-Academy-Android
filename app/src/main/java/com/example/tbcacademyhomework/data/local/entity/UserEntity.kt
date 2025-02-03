package com.example.tbcacademyhomework.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val avatar: String?,
    val firstName: String,
    val lastName: String,
    val about: String?,
    val activationStatus: Double
)
