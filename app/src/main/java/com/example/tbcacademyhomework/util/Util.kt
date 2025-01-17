package com.example.tbcacademyhomework.util

import android.app.DatePickerDialog
import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.widget.PopupMenu
import java.util.Calendar

fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            onDateSelected("$selectedDay/${selectedMonth + 1}/$selectedYear")
        },
        year,
        month,
        day
    )

    datePickerDialog.show()
}

fun showDropdownMenu(
    view: View,
    dropDownItems: List<String>,
    onItemSelected: (String) -> Unit
) {
    val popupMenu = PopupMenu(view.context, view)

    dropDownItems.forEachIndexed { index, item ->
        popupMenu.menu.add(0, index, index, item)
    }

    popupMenu.setOnMenuItemClickListener { item: MenuItem ->
        onItemSelected(item.title.toString())
        true
    }
    popupMenu.show()
}