package com.example.tbcacademyhomework.domain.managers.language.usecase

import com.example.tbcacademyhomework.domain.managers.language.repository.LanguageRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {
    operator fun invoke() = languageRepository.getSelectedLanguage()
}