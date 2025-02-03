package com.example.tbcacademyhomework.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.tbcacademyhomework.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity")
    fun getUsers(): Flow<List<UserEntity>>

    @Upsert
    fun upsertUsers(users: List<UserEntity>)
}