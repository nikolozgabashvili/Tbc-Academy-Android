package com.example.tbcacademyhomework.product

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.tbcacademyhomework.R

enum class ProductColor(@ColorRes val color: Int, @StringRes val displayName: Int) {
    BLUE_GRAY(R.color.blue_gray, R.string.blue_gray),
    BLACK(R.color.black, R.string.black),
    BROWN(R.color.brown, R.string.brown),
}