package com.example.tbcacademyhomework.domain.core.managers.language.repository

import com.example.tbcacademyhomework.domain.core.managers.language.AppLanguage
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    val languageChangeEvent: Flow<Unit>
    fun getAvailableLanguages(): List<AppLanguage>
    suspend fun getSelectedLanguage(): Flow<AppLanguage>
    suspend fun setSelectedLanguage(language: AppLanguage)
}