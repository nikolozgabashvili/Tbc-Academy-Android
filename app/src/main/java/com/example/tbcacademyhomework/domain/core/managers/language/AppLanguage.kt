package com.example.tbcacademyhomework.domain.core.managers.language

import com.example.tbcacademyhomework.domain.core.managers.language.LanguageConsts.LANGUAGE_EMOJI_GE
import com.example.tbcacademyhomework.domain.core.managers.language.LanguageConsts.LANGUAGE_EMOJI_US
import com.example.tbcacademyhomework.domain.core.managers.language.LanguageConsts.LANGUAGE_ENGLISH
import com.example.tbcacademyhomework.domain.core.managers.language.LanguageConsts.LANGUAGE_GEORGIAN
import com.example.tbcacademyhomework.domain.core.managers.language.LanguageConsts.LANGUAGE_LOCALE_EN
import com.example.tbcacademyhomework.domain.core.managers.language.LanguageConsts.LANGUAGE_LOCALE_KA

enum class AppLanguage(
    val displayString: String,
    val emojiString: String,
    val localeString: String,
) {
    GEORGIAN(
        displayString = LANGUAGE_GEORGIAN,
        emojiString = LANGUAGE_EMOJI_GE,
        localeString = LANGUAGE_LOCALE_KA,
    ),
    ENGLISH(
        displayString = LANGUAGE_ENGLISH,
        emojiString = LANGUAGE_EMOJI_US,
        localeString = LANGUAGE_LOCALE_EN,
    )
}

