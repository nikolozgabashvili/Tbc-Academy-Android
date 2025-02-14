package com.example.tbcacademyhomework.data.util

import com.example.tbcacademyhomework.data.models.PlaceDto
import com.example.tbcacademyhomework.domain.models.Place

fun PlaceDto.toPlace(): Place {
    println(rate)
    return Place(
        id = id,
        cover = cover,
        price = price,
        title = title,
        reactionCount = reactionCount,
        rating = rate ?: 0,
        location = location
    )
}