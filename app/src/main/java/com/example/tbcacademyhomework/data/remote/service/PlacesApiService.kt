package com.example.tbcacademyhomework.data.remote.service

import com.example.tbcacademyhomework.data.models.PlaceDto
import retrofit2.Response
import retrofit2.http.GET

interface PlacesApiService {

    @GET(PLACES_ENDPOINT)
    suspend fun getPlaces(): Response<List<PlaceDto>>


    companion object {
        private const val PLACES_ENDPOINT = "6dffd14a-836f-4566-b024-bd41ace3a874"
    }
}