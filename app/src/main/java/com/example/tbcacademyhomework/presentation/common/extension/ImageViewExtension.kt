package com.example.tbcacademyhomework.presentation.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.R


fun ImageView.loadImage(img: String?) {
    Glide.with(this)
        .load(img)
        .error(R.drawable.ic_account)
        .into(this)

}