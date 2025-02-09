package com.example.tbcacademyhomework.presentation.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun showToast(context: Context, text: String?) {
    Toast.makeText(context, text ?: "", Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int) {
    Glide.with(context).load(url).placeholder(placeholder).error(placeholder).into(this)
}