package com.example.tbcacademyhomework

import android.app.Application
import androidx.room.Room
import com.example.tbcacademyhomework.data.app_module.AppModuleImpl
import com.example.tbcacademyhomework.data.local.database.AppDatabase
import com.example.tbcacademyhomework.domain.app_module.AppModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(applicationContext)
        database = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"app-room.db").build()
    }

    companion object {
        lateinit var appModule: AppModule
        lateinit var database: AppDatabase
    }
}