package com.example.tbcacademyhomework.domain.managers.language.usecase

import com.example.tbcacademyhomework.domain.managers.language.AppLanguage
import com.example.tbcacademyhomework.domain.managers.language.repository.LanguageRepository
import javax.inject.Inject

class ChangeLanguageUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(language: AppLanguage) {
        languageRepository.setSelectedLanguage(language)
    }
}