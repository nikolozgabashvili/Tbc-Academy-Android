package com.example.tbcacademyhomework.presentation.core.managers.language.screen

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.core.managers.language.AppLanguage
import com.example.tbcacademyhomework.domain.core.managers.language.usecase.ChangeLanguageUseCase
import com.example.tbcacademyhomework.domain.core.managers.language.usecase.GetAvailableLanguagesUseCase
import com.example.tbcacademyhomework.domain.core.managers.language.usecase.GetLanguageUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: ChangeLanguageUseCase,
    getAvailableLanguagesUseCase: GetAvailableLanguagesUseCase
) : ViewModel() {

    private val availableLanguages = getAvailableLanguagesUseCase()

    private val _state = MutableStateFlow(LanguageScreenState())
    val state = _state.asStateFlow()

    init {
        launchCoroutineScope {
            getLanguageUseCase().collect { language ->
                val languages = availableLanguages.map {
                    AppLanguageItem(
                        language = it,
                        isSelected = it == language,
                        hadUnderLine = it != availableLanguages.last()
                    )
                }
                _state.update { it.copy(languages = languages) }

            }
        }
    }

    fun onLanguageSelected(language: AppLanguage) {
        val newLanguages =
            _state.value.languages.map { it.copy(isSelected = it.language == language) }
        _state.update { it.copy(languages = newLanguages) }
        launchCoroutineScope {
            setLanguageUseCase(language)
        }
    }


}