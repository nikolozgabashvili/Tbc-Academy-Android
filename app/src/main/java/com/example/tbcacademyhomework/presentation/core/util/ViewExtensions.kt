package com.example.tbcacademyhomework.presentation.core.util

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
    image: GenericImage?
) {
    when (image) {
        is GenericImage.Resource -> {
            loadImage(image.resId)
        }

        is GenericImage.NetworkImage -> {
            val glideRequest = Glide.with(context).load(image.url)
            image.error?.let { glideRequest.error(it) }
            image.placeHolder?.let { glideRequest.placeholder(it) }

            glideRequest.into(this)
        }

        null -> Unit
    }
    Glide.with(this)
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