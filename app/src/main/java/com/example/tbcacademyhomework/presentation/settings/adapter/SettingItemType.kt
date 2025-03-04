package com.example.tbcacademyhomework.presentation.settings.adapter

import com.example.tbcacademyhomework.R

enum class SettingItemType(
    val iconRes: Int,
    val titleRes: Int
) {
    PROFILE(
        iconRes = R.drawable.ic_person,
        titleRes = R.string.profile
    ),
    THEME(
        iconRes = R.drawable.ic_day_night,
        titleRes = R.string.day_night_theme

    ),
    LANGUAGE(
        iconRes = R.drawable.ic_language,
        titleRes = R.string.language
    ),
}