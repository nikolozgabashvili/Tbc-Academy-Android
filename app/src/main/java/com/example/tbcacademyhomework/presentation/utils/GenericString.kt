package com.example.tbcacademyhomework.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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

@Composable
fun GenericString.getValue(): String? {
    val context = LocalContext.current
    return when (this) {
        is GenericString.Literal -> this.text
        is GenericString.Resource -> context.getString(this.resource)
    }
}