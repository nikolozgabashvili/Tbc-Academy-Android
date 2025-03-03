package com.example.tbcacademyhomework.domain.core.managers.theme.usecase

import com.example.tbcacademyhomework.domain.core.managers.theme.Theme
import com.example.tbcacademyhomework.domain.core.managers.theme.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): Flow<Theme> {
        return themeRepository.getTheme()
    }
}