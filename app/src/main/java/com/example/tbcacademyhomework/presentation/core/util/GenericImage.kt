package com.example.tbcacademyhomework.presentation.core.util

import androidx.annotation.DrawableRes

sealed interface GenericImage {
    data class Resource(
        @DrawableRes val resId: Int?
    ) : GenericImage

    data class NetworkImage(
        val url: String,
        @DrawableRes val placeHolder: Int? = null,
        @DrawableRes val error: Int? = placeHolder
    ):GenericImage

}