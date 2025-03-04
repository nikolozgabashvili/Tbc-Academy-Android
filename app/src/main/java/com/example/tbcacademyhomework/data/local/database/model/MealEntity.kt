package com.example.tbcacademyhomework.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String?,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val image: String?,
    val youtubeVideo: String?,
    val ingredientMap: String?
)