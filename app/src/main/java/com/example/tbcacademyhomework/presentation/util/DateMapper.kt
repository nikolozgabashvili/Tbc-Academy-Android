package com.example.tbcacademyhomework.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateMapper {

    fun format(timestamp: Long): String {
        val formatter = SimpleDateFormat("d MMMM 'at' h:mm a", Locale.getDefault())
        return formatter.format(Date(timestamp))
    }


}

