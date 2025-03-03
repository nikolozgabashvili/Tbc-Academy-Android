package com.example.tbcacademyhomework.domain.core.managers.language.usecase

import com.example.tbcacademyhomework.domain.core.managers.language.repository.LanguageRepository
import javax.inject.Inject

class GetAvailableLanguagesUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {
    operator fun invoke() = languageRepository.getAvailableLanguages()
}