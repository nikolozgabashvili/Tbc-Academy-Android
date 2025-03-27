package com.example.tbcacademyhomework.presentation.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: Int) {
    Glide.with(this).load(image).into(this)
}


fun ImageView.loadImage(image: ByteArray) {
    Glide.with(this).load(image).into(this)
}