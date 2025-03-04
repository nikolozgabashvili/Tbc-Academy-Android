package com.example.tbcacademyhomework.data.remote.meal.service

import com.example.tbcacademyhomework.data.remote.meal.model.MealCategoryListDTO
import com.example.tbcacademyhomework.data.remote.meal.model.MealDetailListDTO
import com.example.tbcacademyhomework.data.remote.meal.model.MealListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET(CATEGORY)
    suspend fun getCategories(): Response<MealCategoryListDTO>

    @GET(MEAL)
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): Response<MealListDTO>

    @GET(MEAL_DETAIL)
    suspend fun getMealDetail(
        @Query("i") id: String
    ): Response<MealDetailListDTO>


    companion object {
        private const val CATEGORY = "categories.php"
        private const val MEAL = "filter.php"
        private const val MEAL_DETAIL = "lookup.php"
    }
}