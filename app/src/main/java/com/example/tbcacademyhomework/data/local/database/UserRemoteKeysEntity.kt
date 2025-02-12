package com.example.tbcacademyhomework.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userKeys")
data class UserRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)