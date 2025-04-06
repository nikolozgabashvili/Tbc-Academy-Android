package com.example.tbcacademyhomework.presentation.utils

import android.content.Context
import androidx.annotation.StringRes

sealed interface GenericStrings {
    data class Literal(val text: String?) : GenericStrings
    data class Resource(@StringRes val resource: Int) : GenericStrings
}


fun GenericStrings.getValue(context: Context): String? {
    return when (this) {
        is GenericStrings.Literal -> this.text
        is GenericStrings.Resource -> context.getString(this.resource)
    }
}
