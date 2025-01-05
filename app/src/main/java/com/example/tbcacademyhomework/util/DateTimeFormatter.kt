package com.example.tbcacademyhomework.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DateTimeFormatter {
    private val calendar = Calendar.getInstance()

    private fun isToday(time: Long): Boolean {
        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = time

        return calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)
    }

    private fun isYesterday(time: Long): Boolean {
        val yesterdayCalendar = Calendar.getInstance()
        yesterdayCalendar.add(Calendar.DAY_OF_YEAR, -1)
        yesterdayCalendar.timeInMillis = time

        return calendar.get(Calendar.YEAR) == yesterdayCalendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == yesterdayCalendar.get(Calendar.DAY_OF_YEAR)
    }


    fun formatTime(time: Long): String {
        val dateFormat = SimpleDateFormat("h:mma", Locale.getDefault())
        return if (isToday(time)) {

            "$TODAY, ${dateFormat.format(time)}"
        } else if (isYesterday(time)) {
            "$YESTERDAY, ${dateFormat.format(time)}"
        } else {
            val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            newFormat.format(time)
        }
    }


    companion object {
        const val TODAY = "Today"
        const val YESTERDAY = "Yesterday"
    }
}