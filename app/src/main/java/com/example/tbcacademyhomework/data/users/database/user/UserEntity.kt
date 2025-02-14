package com.example.tbcacademyhomework.data.users.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val page: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)