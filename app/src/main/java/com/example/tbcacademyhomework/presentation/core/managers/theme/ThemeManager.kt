package com.example.tbcacademyhomework.presentation.core.managers.theme

import androidx.appcompat.app.AppCompatDelegate
import com.example.tbcacademyhomework.domain.managers.theme.Theme
import com.example.tbcacademyhomework.domain.managers.theme.usecase.GetSelectedThemeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ThemeManager @Inject constructor(
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase
) : CoroutineScope {
    fun init() {
        launch {
            getSelectedThemeUseCase().collect {
                applyTheme(it)
            }
        }
    }

    private fun applyTheme(theme: Theme) {
        val mode = when (theme) {
            Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            Theme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()
}