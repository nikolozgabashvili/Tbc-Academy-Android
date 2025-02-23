package com.example.tbcacademyhomework.presentation.util.ext

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(
    resourceId: Int,
    placeholderResId: Int = android.R.color.transparent,
    errorResId: Int = android.R.color.transparent
) {
    Glide.with(this.context)
        .load(resourceId)
        .placeholder(placeholderResId)
        .error(errorResId)
        .into(this)
}

fun ImageView.loadImage(
    image: String,
    placeholderResId: Int = android.R.color.transparent,
    errorResId: Int = android.R.color.transparent
) {
    Glide.with(this.context)
        .load(image)
        .placeholder(placeholderResId)
        .error(errorResId)
        .into(this)
}
