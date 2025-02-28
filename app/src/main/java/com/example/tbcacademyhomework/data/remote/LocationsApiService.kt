package com.example.tbcacademyhomework.data.remote

import com.example.tbcacademyhomework.data.remote.model.LocationDTO
import retrofit2.Response
import retrofit2.http.GET

interface LocationsApiService {

    @GET(LOCATIONS)
    suspend fun getLocations(): Response<List<LocationDTO>>


    companion object {
        private const val LOCATIONS = "c4c64996-4ed9-4cbc-8986-43c4990d495a"
    }

}