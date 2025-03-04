package com.example.tbcacademyhomework.presentation.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.bumptech.glide.Glide


fun ImageView.loadImage(
    url: String?,
    placeholder: Int? = null,
    error: Int? = null
) {

    val glideRequest = Glide.with(context).load(url)
    error?.let { glideRequest.error(it) }
    placeholder?.let { glideRequest.placeholder(it) }

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