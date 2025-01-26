package com.example.tbcacademyhomework.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserParams(
    val email: String,
    val password: String
) : Parcelable
