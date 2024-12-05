package com.example.tbcacademyhomework

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.tbcacademyhomework.validators.ValidationResult

fun TextView.setValidation(validationResult: ValidationResult, context: Context) {
    if (validationResult.isError && validationResult.errorMessage != null) {
        this.visibility = View.VISIBLE
        this.text = context.getString(validationResult.errorMessage)
    } else {
        this.visibility = View.GONE
    }
}

fun TextView.clearText() {
    this.text = ""
}
