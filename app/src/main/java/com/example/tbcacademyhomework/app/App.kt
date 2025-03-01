package com.example.tbcacademyhomework.app

import android.app.Application
import com.example.tbcacademyhomework.presentation.core.managers.theme.ThemeManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate() {
        super.onCreate()
        themeManager.init()
    }
}