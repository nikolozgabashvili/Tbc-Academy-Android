package com.example.tbcacademyhomework.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbcacademyhomework.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}