package com.example.tbcacademyhomework.presentation.core.managers.language.screen

import com.example.tbcacademyhomework.domain.core.managers.language.AppLanguage

data class AppLanguageItem(
    val language: AppLanguage,
    val isSelected: Boolean,
    val hadUnderLine:Boolean
)