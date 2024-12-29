package com.example.tbcacademyhomework


import java.text.SimpleDateFormat
import java.util.Locale



fun Long.toDateString(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(this)
}


fun String.capitalize() = this.lowercase().replaceFirstChar {
    it.titlecase(
        Locale.getDefault()
    )
}