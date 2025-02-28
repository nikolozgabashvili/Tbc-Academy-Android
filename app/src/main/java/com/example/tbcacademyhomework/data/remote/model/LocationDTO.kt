package com.example.tbcacademyhomework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val lat: Double,
    @SerialName("lan")
    val long: Double,
    val title: String,
    val address: String
)
