package com.example.tbcacademyhomework.data.util

import com.example.tbcacademyhomework.data.remote.model.LocationDTO
import com.example.tbcacademyhomework.domain.model.LatLongDomain
import com.example.tbcacademyhomework.domain.model.LocationDomain


private fun LocationDTO.toDomain(): LocationDomain {
    return LocationDomain(
        latLongDomain = LatLongDomain(lat, long),
        title = title,
        address = address
    )
}

fun List<LocationDTO>.toDomain(): List<LocationDomain> {
    return this.map { it.toDomain() }
}