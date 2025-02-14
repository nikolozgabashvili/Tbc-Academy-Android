package com.example.tbcacademyhomework.data.users.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbcacademyhomework.data.users.database.user.UserDao
import com.example.tbcacademyhomework.data.users.database.user.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}