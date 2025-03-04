package com.example.tbcacademyhomework.data.remote.core.managers.language.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbcacademyhomework.domain.core.datastore.DataStoreHelper
import com.example.tbcacademyhomework.domain.core.managers.language.AppLanguage
import com.example.tbcacademyhomework.domain.core.managers.language.repository.LanguageRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val datastore: DataStoreHelper
) : LanguageRepository {

    private val languageEvent = Channel<Unit>()

    override val languageChangeEvent: Flow<Unit>
        get() = languageEvent.receiveAsFlow()

    override fun getAvailableLanguages(): List<AppLanguage> {
        return AppLanguage.entries
    }

    override suspend fun getSelectedLanguage(): Flow<AppLanguage> {
        return datastore.getValue(LANGUAGE_KEY, AppLanguage.GEORGIAN.name)
            .map { AppLanguage.valueOf(it) }
    }

    override suspend fun setSelectedLanguage(language: AppLanguage) {
        datastore.setValue(LANGUAGE_KEY, language.name)
        languageEvent.send(Unit)
    }

    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("language")
    }


}