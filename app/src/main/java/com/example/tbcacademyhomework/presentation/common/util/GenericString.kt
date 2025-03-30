package com.example.tbcacademyhomework.presentation.common.util

import android.content.Context
import androidx.annotation.StringRes

sealed interface GenericString {
    data class Literal(val text: String?) : GenericString
    data class Resource(@StringRes val resId: Int) : GenericString
}

fun GenericString.getValue(context: Context): String? {
    return when (this) {
        is GenericString.Literal -> text
        is GenericString.Resource -> context.getString(resId)
    }
}