package com.example.tbcacademyhomework


import android.view.View
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun EditText.setupColors(activeColor: Int, inactiveColor: Int) {
    this.doOnTextChanged { text, _, _, _ ->
        if (text.isNullOrEmpty()) {
            this.setTextColor(inactiveColor)
            this.compoundDrawablesRelative.forEach { drawable ->
                drawable?.setTint(inactiveColor)
            }
        } else {
            this.setTextColor(
                activeColor
            )
            this.compoundDrawablesRelative.forEach { drawable ->
                drawable?.setTint(activeColor)
            }
        }
    }
}