package com.example.tbcacademyhomework.presentation.views

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.example.tbcacademyhomework.R

enum class ButtonType(
    val value: Int,
    @DrawableRes val backgroundDrawable: Int,
    @ColorRes val contentColor: Int,
    @RawRes val loader: Int
) {
    PRIMARY(
        value = 0,
        backgroundDrawable = R.drawable.bg_button_primary,
        contentColor = R.color.white,
        loader = R.raw.loader_primary
    ),
    SECONDARY(
        value = 1,
        backgroundDrawable = R.drawable.bg_button_secondary,
        contentColor = R.color.black,
        loader = R.raw.loader_secondary
    );

    companion object {
        fun getEnumForValue(value: Int): ButtonType {
            return ButtonType.entries.firstOrNull { it.value == value } ?: PRIMARY
        }
    }
}