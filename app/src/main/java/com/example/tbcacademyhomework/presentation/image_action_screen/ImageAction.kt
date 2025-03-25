package com.example.tbcacademyhomework.presentation.image_action_screen

import com.example.tbcacademyhomework.R

enum class ImageAction(
    val image: Int,
    val title: Int
) {
    SELECT_FROM_LOCALE(image = R.drawable.ic_image, title = R.string.select_image),
    TAKE_PHOTO(image = R.drawable.ic_take_image, title = R.string.take_photo),
}
