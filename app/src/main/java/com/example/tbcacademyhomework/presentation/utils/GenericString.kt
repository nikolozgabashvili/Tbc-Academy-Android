package com.example.tbcacademyhomework.presentation.utils

import android.content.Context
import androidx.annotation.StringRes

sealed interface GenericString {
    data class Literal(val text: String?) : GenericString
    data class Resource(@StringRes val resource: Int) : GenericString
}


fun GenericString.getValue(context: Context): String? {
    return when (this) {
        is GenericString.Literal -> this.text
        is GenericString.Resource -> context.getString(this.resource)
    }
}