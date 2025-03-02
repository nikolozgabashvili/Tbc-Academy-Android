package com.example.tbcacademyhomework.presentation.core.managers.language

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import com.example.tbcacademyhomework.domain.managers.language.usecase.GetLanguageEventUseCase
import com.example.tbcacademyhomework.domain.managers.language.usecase.GetLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

class LanguageManager @Inject constructor(
    private val getLanguageEventUseCase: GetLanguageEventUseCase,
    private val getLanguageUseCase: GetLanguageUseCase
)  {

    fun getLanguageEvent() = getLanguageEventUseCase()

    fun setLanguage(context: Context): Context {
        val currentLanguage = runBlocking {
            getLanguageUseCase().first()
        }
        val newLocale = Locale(currentLanguage.localeString)
        Locale.setDefault(newLocale)
        val config = Configuration(context.resources.configuration)
        config.setLocales(LocaleList(newLocale))
        return context.createConfigurationContext(config)
    }

}