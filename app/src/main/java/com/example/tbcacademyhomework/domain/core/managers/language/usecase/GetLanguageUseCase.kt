package com.example.tbcacademyhomework.domain.core.managers.language.usecase

import com.example.tbcacademyhomework.domain.core.managers.language.repository.LanguageRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke() = languageRepository.getSelectedLanguage()
}