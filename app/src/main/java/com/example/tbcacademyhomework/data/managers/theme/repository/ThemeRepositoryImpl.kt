package com.example.tbcacademyhomework.data.managers.theme.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbcacademyhomework.domain.core.datastore.DataStoreHelper
import com.example.tbcacademyhomework.domain.managers.theme.Theme
import com.example.tbcacademyhomework.domain.managers.theme.repository.ThemeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ThemeRepositoryImpl @Inject constructor(
    private val datastore: DataStoreHelper
) : ThemeRepository, CoroutineScope {


    private var themeFlow: Flow<Theme> = flowOf()

    init {
        launch {
            themeFlow = datastore.getValue(THEME_KEY, Theme.SYSTEM.name).map { Theme.valueOf(it) }
        }
    }


    override fun getTheme() = themeFlow

    override suspend fun setTheme(theme: Theme) {
        datastore.setValue(THEME_KEY, theme.name)
    }

    override fun getAvailableThemes() = Theme.entries

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()


}