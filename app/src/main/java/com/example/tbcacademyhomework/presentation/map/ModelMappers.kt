package com.example.tbcacademyhomework.presentation.map

import com.example.tbcacademyhomework.domain.model.LatLongDomain
import com.example.tbcacademyhomework.domain.model.LocationDomain
import com.example.tbcacademyhomework.presentation.map.model.LatLong
import com.example.tbcacademyhomework.presentation.map.model.Location

fun LocationDomain.toUi(): Location {
    return Location(
        latLong = latLongDomain.toUi(),
        locationTitle = title,
        locationAddress = address

    )
}

private fun LatLongDomain.toUi(): LatLong {
    return LatLong(
        lat = lat,
        long = long
    )
}