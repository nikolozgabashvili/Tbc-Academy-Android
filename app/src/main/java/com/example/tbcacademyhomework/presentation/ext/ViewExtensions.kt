package com.example.tbcacademyhomework.presentation.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String?) {
    message?.let {
        Snackbar.make(this, it, Snackbar.LENGTH_SHORT).show()
    }
}

