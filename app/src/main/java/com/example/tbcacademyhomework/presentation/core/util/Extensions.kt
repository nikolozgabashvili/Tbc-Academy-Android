package com.example.tbcacademyhomework.presentation.core.util

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray

@Suppress("DEPRECATION")
fun Bundle?.getSparseParcelableArraySafe(key: String): SparseArray<Parcelable>? =
    try {
        this?.takeIf { it.containsKey(key) }?.let {
            if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getSparseParcelableArray(key, Parcelable::class.java)
            } else {
                getSparseParcelableArray(key)
            }
        }
    } catch (_: Exception) {
        null
    }

@Suppress("DEPRECATION")
fun Bundle?.getParcelableSafe(key: String): Parcelable? =
    try {
        this?.let {
            if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getParcelable(key, Parcelable::class.java)
            } else {
                getParcelable(key)
            }
        }
    } catch (_: Exception) {
        null
    }