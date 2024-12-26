package com.example.tbcacademyhomework.adapters

import androidx.annotation.DrawableRes
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.models.LocationIcon

class LocationIconsHelper {

    private enum class Icons(@DrawableRes val iconRes: Int) {
        HOME(R.drawable.ic_house),
        OFFICE(R.drawable.ic_office),
        OTHER(R.drawable.ic_other)
    }

    private val icons = Icons.entries.map { icon ->
        LocationIcon(
            id = icon.ordinal + 1,
            iconRes = icon.iconRes,
            isSelected = icon.ordinal == 0
        )
    }.toMutableList()

    private var listener: () -> Unit = {}

    private var selectedIconId = 0

    init {
        selectedIconId = icons.first().id
    }

    fun onDataChange(listener: () -> Unit) {
        this.listener = listener
    }

    fun selectItem(id: Int) {

        selectedIconId = id
        icons.replaceAll { it.copy(isSelected = it.id == id) }
        listener()
    }

    fun getSelectedIconDrawable(): Int {
        return icons.find { it.id == selectedIconId }?.iconRes ?: R.drawable.ic_other
    }

    fun getData(): List<LocationIcon> {
        return icons.toList()
    }

    fun setActiveIconWithDrawable(@DrawableRes drawable: Int) {
        val icon = icons.find { it.iconRes == drawable }
        icon?.let {
            selectItem(icon.id)
        }

    }


}