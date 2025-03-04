package com.example.tbcacademyhomework.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbcacademyhomework.data.local.database.dao.MealDao
import com.example.tbcacademyhomework.data.local.database.model.MealEntity

@Database(entities = [MealEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}