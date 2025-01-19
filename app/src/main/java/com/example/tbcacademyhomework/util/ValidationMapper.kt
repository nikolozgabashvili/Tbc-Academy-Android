package com.example.tbcacademyhomework.util

import android.content.Context
import com.example.tbcacademyhomework.R

fun ValidationError?.toErrorString(context: Context): String? {

    return when (this) {
        ValidationError.EMPTY_FIELD -> context.getString(R.string.empty_field)
        ValidationError.INVALID_EMAIL -> context.getString(R.string.invalid_email)
        else -> null
    }
}
