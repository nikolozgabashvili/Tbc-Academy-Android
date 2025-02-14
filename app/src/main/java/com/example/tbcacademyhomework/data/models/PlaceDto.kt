package com.example.tbcacademyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    @SerialName("reaction_count")
    val reactionCount: Int,
    val location: String,
    val rate: Int?
)
