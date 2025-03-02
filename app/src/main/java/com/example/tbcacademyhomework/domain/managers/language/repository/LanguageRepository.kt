package com.example.tbcacademyhomework.domain.managers.language.repository

import com.example.tbcacademyhomework.domain.managers.language.AppLanguage
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    val languageChangeEvent: Flow<Unit>
    fun getAvailableLanguages(): List<AppLanguage>
    fun getSelectedLanguage(): Flow<AppLanguage>
    suspend fun setSelectedLanguage(language: AppLanguage)
}