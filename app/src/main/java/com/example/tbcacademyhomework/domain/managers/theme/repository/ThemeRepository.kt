package com.example.tbcacademyhomework.domain.managers.theme.repository

import com.example.tbcacademyhomework.domain.managers.theme.Theme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
    fun getAvailableThemes(): List<Theme>
}