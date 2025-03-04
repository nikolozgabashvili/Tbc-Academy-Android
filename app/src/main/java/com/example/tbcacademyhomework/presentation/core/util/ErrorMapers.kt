package com.example.tbcacademyhomework.presentation.core.util

import android.content.Context
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.core.util.DataError

fun DataError.toErrorString(context: Context): String {
    return when (this) {
        DataError.DynamicError.NETWORK_ERROR -> {
            context.getString(R.string.network_error)
        }

        DataError.DynamicError.UNKNOWN -> {
            context.getString(R.string.unknown_error)
        }

        is DataError.Literal -> {
            this.errorString ?: ""
        }
    }
}