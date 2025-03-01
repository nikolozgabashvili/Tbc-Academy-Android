package com.example.tbcacademyhomework.presentation.core.managers.theme

import com.example.tbcacademyhomework.domain.managers.theme.Theme

data class ThemeItem(
    val theme: Theme,
    val isSelected: Boolean,
    val hasUnderLine: Boolean
)
