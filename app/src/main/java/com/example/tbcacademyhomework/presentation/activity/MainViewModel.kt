package com.example.tbcacademyhomework.presentation.activity

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.presentation.core.managers.language.LanguageManager
import com.example.tbcacademyhomework.presentation.core.managers.theme.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    languageManager: LanguageManager,
    private val themeManager: ThemeManager
) : ViewModel() {
    val restartActivityEvent = languageManager.getLanguageEvent()

    val themeSet: Boolean
        get() = themeManager.themeSet
}