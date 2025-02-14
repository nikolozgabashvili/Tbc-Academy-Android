package com.example.tbcacademyhomework.data.users.database.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUsers(users: List<UserEntity>)

    @Query("DELETE FROM USERS WHERE page = :page")
    suspend fun deleteUsersByPage(page: Int)

    @Query("select * from users where page <= :page order by page,id asc")
    suspend fun getUsersBeforePage(page: Int): List<UserEntity>

    @Query("SELECT MAX(page) FROM users")
    suspend fun getLoadedPageNumber(): Int


    @Query("DELETE FROM USERS")
    suspend fun clearAll()

}