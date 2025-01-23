package com.example.tbcacademyhomework.util

import android.content.Context
import com.example.tbcacademyhomework.R

fun ErrorType.getError(context: Context): String {
    return when (this) {
        ErrorType.NO_INTERNET -> context.getString(R.string.no_internet_error)
        ErrorType.SERIALIZATION_ERROR -> context.getString(R.string.serialization_error)
        ErrorType.UNKNOWN_ERROR -> context.getString(R.string.unknown_error)
        ErrorType.UNKNOWN_HOST -> context.getString(R.string.unknown_host)
    }
}