package com.example.tbcacademyhomework.data.remote.repository

import com.example.tbcacademyhomework.data.remote.service.PlacesApiService
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.data.util.toPlace
import com.example.tbcacademyhomework.domain.models.Place
import com.example.tbcacademyhomework.domain.repository.PlacesRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val placesApiService: PlacesApiService,
    private val httpRequestHandler: HttpRequestHelper
) : PlacesRepository {

    override fun getPlaces(): Flow<Resource<List<Place>, DataError>> {
        return flow {
            emit(Resource.Loading)
            httpRequestHandler.safeCall {
                placesApiService.getPlaces()
            }.map { places -> places.map { it.toPlace() } }.also { emit(it) }

        }.flowOn(Dispatchers.IO)

    }
}