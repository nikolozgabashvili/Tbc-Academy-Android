package com.example.tbcacademyhomework.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, UserRemoteKeysEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: UserDao
    abstract val remoteKeysDao: RemoteKeysDao
}