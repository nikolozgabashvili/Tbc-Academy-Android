package com.example.tbcacademyhomework.data.core.managers.language.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbcacademyhomework.domain.core.datastore.DataStoreHelper
import com.example.tbcacademyhomework.domain.managers.language.AppLanguage
import com.example.tbcacademyhomework.domain.managers.language.repository.LanguageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LanguageRepositoryImpl @Inject constructor(
    private val datastore: DataStoreHelper
) : LanguageRepository, CoroutineScope {
    private var languageFlow: Flow<AppLanguage> = flowOf()

    init {
        launch {
            languageFlow = datastore.getValue(LANGUAGE_KEY, AppLanguage.GEORGIAN.name)
                .map { AppLanguage.valueOf(it) }
        }
    }

    private val languageEvent = Channel<Unit>()

    override val languageChangeEvent: Flow<Unit>
        get() = languageEvent.receiveAsFlow()

    override fun getAvailableLanguages(): List<AppLanguage> {
        return AppLanguage.entries
    }

    override fun getSelectedLanguage(): Flow<AppLanguage> {
        return languageFlow
    }

    override suspend fun setSelectedLanguage(language: AppLanguage) {
        datastore.setValue(LANGUAGE_KEY, language.name)
        languageEvent.send(Unit)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("language")
    }


}