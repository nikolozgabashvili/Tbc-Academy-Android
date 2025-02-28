package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.model.LocationDomain
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocations(): Flow<Resource<List<LocationDomain>, DataError>>
}