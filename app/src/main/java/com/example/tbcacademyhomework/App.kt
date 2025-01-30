package com.example.tbcacademyhomework

import android.annotation.SuppressLint
import android.app.Application
import com.example.tbcacademyhomework.user.UserDataStoreImpl

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        dataStore = UserDataStoreImpl(applicationContext)

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var dataStore: UserDataStoreImpl
    }
}