package com.example.tbcacademyhomework.presentation.register

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterParams(
    val email: String,
    val password: String
) : Parcelable
