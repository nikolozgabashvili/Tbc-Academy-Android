package com.example.tbcacademyhomework.presentation.common.extension

import android.view.View
import com.example.tbcacademyhomework.presentation.common.util.GenericString
import com.example.tbcacademyhomework.presentation.common.util.getValue
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String?) {
    message?.let {
        Snackbar.make(this, it, Snackbar.LENGTH_SHORT).show()
    }
}

fun View.showSnackBar(stringRes: Int?) {
    stringRes?.let {
        Snackbar.make(this, it, Snackbar.LENGTH_SHORT).show()
    }
}


fun View.showSnackBar(genericString: GenericString) {
    val message = genericString.getValue(context)
    showSnackBar(message)
}