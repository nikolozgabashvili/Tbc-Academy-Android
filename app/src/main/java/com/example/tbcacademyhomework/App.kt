package com.example.tbcacademyhomework

import android.app.Application
import com.example.tbcacademyhomework.data.app_module.AppModuleImpl
import com.example.tbcacademyhomework.domain.app_module.AppModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(applicationContext)
    }

    companion object {
        lateinit var appModule: AppModule
    }
}