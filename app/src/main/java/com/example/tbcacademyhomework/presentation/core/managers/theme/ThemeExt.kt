package com.example.tbcacademyhomework.presentation.core.managers.theme

import android.content.Context
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.core.managers.theme.Theme

fun Theme.getDisplayableName(context: Context): String {
    return when (this) {
        Theme.SYSTEM -> context.getString(R.string.theme_system)
        Theme.LIGHT -> context.getString(R.string.theme_light)
        Theme.DARK -> context.getString(R.string.theme_dark)
    }
}

fun Theme.getDisplayableDrawable(): Int {
    return when (this) {
        Theme.SYSTEM -> R.drawable.ic_theme_system
        Theme.LIGHT -> R.drawable.ic_theme_light
        Theme.DARK -> R.drawable.ic_theme_dark
    }
}