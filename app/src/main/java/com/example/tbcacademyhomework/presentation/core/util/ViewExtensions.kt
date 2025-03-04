package com.example.tbcacademyhomework.presentation.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.tbcacademyhomework.R


fun ImageView.loadImage(
    url: String?,
    placeholder: Int? = R.drawable.ic_loading,
    error: Int? = R.drawable.ic_filled_error,
    scaleType: ScaleType = ScaleType.FIT_CENTER
) {

    val glideRequest = Glide.with(context).load(url)
    error?.let { glideRequest.error(it) }
    placeholder?.let { glideRequest.placeholder(it) }
    glideRequest.listener(object : RequestListener<Drawable> {


        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: com.bumptech.glide.request.target.Target<Drawable>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            this@loadImage.scaleType = scaleType
            return false
        }

        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            this@loadImage.scaleType = ScaleType.FIT_CENTER
            return false
        }

    })
        .into(this)
    glideRequest.into(this)


}

fun ImageView.loadImage(@DrawableRes resId: Int?) {
    resId?.let {
        this.setImageResource(resId)
    }

}

fun AppCompatImageView.setTintColor(
    @ColorInt color: Int? = null
) {
    color?.let {
        setColorFilter(it)
    }
}

fun AppCompatTextView.setTextColor(
    @ColorInt color: Int? = null
) {
    color?.let {
        setTextColor(it)
    }
}

fun View.setBackgroundDrawable(@DrawableRes resId: Int?) {
    resId?.let { background = ContextCompat.getDrawable(context, it) }
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


fun View.visibleIf(visible: Boolean?, gone: Boolean = true) {
    if (visible == true) {
        show()
    } else {
        if (gone) gone() else hide()
    }
}

fun ViewGroup.saveChildViewStates(): SparseArray<Parcelable> {
    val childViewStates = SparseArray<Parcelable>()
    children.forEach { child -> child.saveHierarchyState(childViewStates) }
    return childViewStates
}


fun ViewGroup.restoreChildViewStates(childViewStates: SparseArray<Parcelable>) {
    children.forEach { child -> child.restoreHierarchyState(childViewStates) }
}

fun Context.openYouTubeVideo(videoUrl: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        intent.setPackage("com.google.android.youtube")
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(browserIntent)
    }
}