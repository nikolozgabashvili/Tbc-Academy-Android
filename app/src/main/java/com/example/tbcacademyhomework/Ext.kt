package com.example.tbcacademyhomework

import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import java.text.SimpleDateFormat
import java.util.Locale



fun Long.toDateString(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(this)
}

fun Button.isSelected(isSelected: Boolean) {
    if (isSelected) {
        this.background = AppCompatResources.getDrawable(context, R.drawable.bg_btn_gray)
        this.setTextColor(context.getColor(R.color.white))
    }else{
        this.background = null
        this.setTextColor(context.getColor(R.color.black))
    }

}