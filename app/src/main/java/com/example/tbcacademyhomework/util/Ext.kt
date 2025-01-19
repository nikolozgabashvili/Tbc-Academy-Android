package com.example.tbcacademyhomework.util

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: String?) {
    text?.let {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}