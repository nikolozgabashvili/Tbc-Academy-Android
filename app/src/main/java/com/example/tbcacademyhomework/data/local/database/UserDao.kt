package com.example.tbcacademyhomework.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun userPagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM USERS")
    suspend fun clearAll()

}