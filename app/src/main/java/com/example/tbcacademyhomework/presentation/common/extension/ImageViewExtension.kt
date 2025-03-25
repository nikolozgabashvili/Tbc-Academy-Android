package com.example.tbcacademyhomework.presentation.common.extension

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: Int) {
    Glide.with(this).load(image).into(this)
}
fun ImageView.loadImage(image: Bitmap) {
    Glide.with(this).load(image).into(this)
}