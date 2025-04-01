package com.example.tbcacademyhomework.presentation.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int) {
    Glide.with(context).load(url).placeholder(placeholder).error(placeholder).into(this)
}