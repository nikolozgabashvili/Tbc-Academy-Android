package com.example.tbcacademyhomework.presentation

import android.content.Context
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.util.DataError

fun DataError.toString(context: Context): String {
    return when (this) {
        DataError.DynamicError.UNKNOWN -> context.getString(R.string.unknown_error)
        is DataError.Literal -> this.error ?: ""
    }
}