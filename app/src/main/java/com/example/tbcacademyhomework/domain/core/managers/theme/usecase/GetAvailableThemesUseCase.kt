package com.example.tbcacademyhomework.domain.core.managers.theme.usecase

import com.example.tbcacademyhomework.domain.core.managers.theme.repository.ThemeRepository
import javax.inject.Inject

class GetAvailableThemesUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke() = themeRepository.getAvailableThemes()

}