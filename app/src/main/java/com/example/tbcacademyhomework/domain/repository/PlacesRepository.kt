package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.models.Place
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    fun getPlaces(): Flow<Resource<List<Place>, DataError>>
}