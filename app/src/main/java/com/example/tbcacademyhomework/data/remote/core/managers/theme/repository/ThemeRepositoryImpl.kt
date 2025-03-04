package com.example.tbcacademyhomework.data.remote.core.managers.theme.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbcacademyhomework.domain.core.datastore.DataStoreHelper
import com.example.tbcacademyhomework.domain.core.managers.theme.Theme
import com.example.tbcacademyhomework.domain.core.managers.theme.repository.ThemeRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val datastore: DataStoreHelper
) : ThemeRepository {


    override suspend fun getTheme() =
        datastore.getValue(THEME_KEY, Theme.SYSTEM.name).map { Theme.valueOf(it) }

    override suspend fun setTheme(theme: Theme) {
        datastore.setValue(THEME_KEY, theme.name)
    }

    override fun getAvailableThemes() = Theme.entries

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
    }
}