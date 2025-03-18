package com.example.tbcacademyhomework.data.category.api_service

import com.example.tbcacademyhomework.data.category.model.EquipmentCategoryDTO
import retrofit2.Response
import retrofit2.http.GET

interface EquipmentCategoryApiService {
    @GET(CATEGORIES)
    suspend fun fetchCategories(): Response<List<EquipmentCategoryDTO>>

    companion object {
        private const val CATEGORIES = "1f8945cc-3516-4917-9971-ddb6ea71b0da"
    }
}