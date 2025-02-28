package com.example.tbcacademyhomework.data.remote.repository

import com.example.tbcacademyhomework.data.remote.LocationsApiService
import com.example.tbcacademyhomework.data.util.toDomain
import com.example.tbcacademyhomework.domain.model.LocationDomain
import com.example.tbcacademyhomework.domain.repository.LocationRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val httpRequestHelper: HttpRequestHelper,
    private val locationsApiService: LocationsApiService
) : LocationRepository {



    override suspend fun getLocations(): Flow<Resource<List<LocationDomain>, DataError>> {
        return httpRequestHelper.safeCall {
            locationsApiService.getLocations()
        }.map { resource ->
            resource.map {
                it.toDomain()
            }
        }


    }
}