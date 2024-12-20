package com.example.tbcacademyhomework

import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.toFormattedDate(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toFormattedDate
    }
    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calendar.time)
}

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

fun View.makeSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}


fun User.display(): String {
    return """
        id = ${this.id}
        firstName = ${this.firstName}
        lastname = ${this.lastName}
        address = ${this.address}
        dateOfBirth = ${this.birthday.toLong().toFormattedDate()}
        desc = ${this.desc}
        email = ${this.email}
    """.trimIndent()

}