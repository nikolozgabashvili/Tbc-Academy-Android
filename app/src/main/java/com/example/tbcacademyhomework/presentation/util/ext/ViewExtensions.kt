package com.example.tbcacademyhomework.presentation.util.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.tbcacademyhomework.R


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
    scaleType: ScaleType = ScaleType.CENTER_CROP,
    placeholderResId: Int = R.drawable.ic_refresh,
    errorResId: Int = R.drawable.ic_image_placeholder
) {
    Glide.with(this.context)
        .load(image)
        .placeholder(placeholderResId)
        .error(errorResId).listener(object : RequestListener<Drawable> {


            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                this@loadImage.scaleType = scaleType
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                this@loadImage.scaleType = ScaleType.CENTER_INSIDE
                return false
            }

        })
        .into(this)
}
