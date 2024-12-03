package com.example.tbcacademyhomework.helper

import androidx.annotation.StringRes
import com.example.tbcacademyhomework.R

enum class Language(@StringRes val displayName: Int) {
    English(R.string.language_eng),
    Georgian(R.string.language_geo),
}