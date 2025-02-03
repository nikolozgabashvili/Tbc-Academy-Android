package com.example.tbcacademyhomework

import android.app.Application
import androidx.room.Room
import com.example.tbcacademyhomework.data.local.UsersDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createDatabaseInstance()
    }


    companion object {
        lateinit var database: UsersDatabase
    }

    private fun createDatabaseInstance() {
        database = Room.databaseBuilder(
            applicationContext,
            UsersDatabase::class.java,
            "users_db"
        ).build()
    }
}