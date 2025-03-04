package com.example.tbcacademyhomework.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.tbcacademyhomework.data.local.database.model.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Upsert
    suspend fun upsertMeal(meal: MealEntity)

    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealById(id: String): MealEntity?

    @Query("SELECT * FROM meals")
    fun getAllMeals(): Flow<List<MealEntity>>

    @Query("DELETE FROM meals")
    suspend fun deleteAllMeals()

    @Query("DELETE FROM meals WHERE id = :id")
    suspend fun deleteMealById(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM meals WHERE id = :mealId)")
     fun isMealFavourite(mealId: String): Flow<Boolean>


}