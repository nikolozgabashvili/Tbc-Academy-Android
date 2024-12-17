package com.example.tbcacademyhomework

import android.content.Context
import android.view.View
import android.widget.TextView


fun TextView.setError(response: Response, context: Context) {
    if (response.isError()) {
        response.message?.let {
            this.text =
                context.getString(it)
            this.isVisible(true)
        }
    } else {
        this.isVisible(false)
    }
}

fun View.isVisible(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}