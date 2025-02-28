package com.example.tbcacademyhomework.presentation.core.util

import android.content.Context
import androidx.annotation.StringRes

sealed class GenericString {
    data class Literal(val text: String?) : GenericString()
    data class Resource(@StringRes val stringRes: Int) : GenericString()

    fun getValue(context: Context): String {
        return when (this) {
            is Literal -> text ?: ""
            is Resource -> context.getString(stringRes)
        }
    }
}