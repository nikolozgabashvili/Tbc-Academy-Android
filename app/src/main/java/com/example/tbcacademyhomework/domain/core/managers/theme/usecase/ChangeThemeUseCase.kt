package com.example.tbcacademyhomework.domain.core.managers.theme.usecase

import com.example.tbcacademyhomework.domain.core.managers.theme.Theme
import com.example.tbcacademyhomework.domain.core.managers.theme.repository.ThemeRepository
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(theme: Theme) {
        themeRepository.setTheme(theme)

    }
}