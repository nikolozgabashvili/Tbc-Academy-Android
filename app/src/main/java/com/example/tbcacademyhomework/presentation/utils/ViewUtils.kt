package com.example.tbcacademyhomework.presentation.utils

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}