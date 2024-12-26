package com.example.tbcacademyhomework.models

import androidx.annotation.DrawableRes

data class LocationIcon(
    val id: Int,
    @DrawableRes
    val iconRes: Int,
    val isSelected: Boolean = false
)
