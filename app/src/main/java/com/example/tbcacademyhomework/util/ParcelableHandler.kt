package com.example.tbcacademyhomework.util

import android.os.Build
import android.os.Bundle

@Suppress("DEPRECATION")

fun <T> getParcelable(bundle: Bundle, key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle.getParcelable(key, clazz)
    } else {
        bundle.getParcelable(key) as? T
    }
}
