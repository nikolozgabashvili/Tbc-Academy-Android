package com.example.tbcacademyhomework.models

import androidx.annotation.DrawableRes

data class Address(
    val id: Int,
    @DrawableRes
    val image: Int,
    val title: String,
    val address: String,
    val isSelected: Boolean = false
)