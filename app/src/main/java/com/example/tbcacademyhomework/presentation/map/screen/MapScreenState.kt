package com.example.tbcacademyhomework.presentation.map.screen

import com.example.tbcacademyhomework.presentation.map.model.Location

data class MapScreenState(
    val loading: Boolean = false,
    val locations: List<Location> = emptyList(),
    val hasLocationPermission: Boolean = false,
    val hasLocationEnabled: Boolean = false
)
