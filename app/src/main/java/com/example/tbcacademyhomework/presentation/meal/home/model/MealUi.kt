package com.example.tbcacademyhomework.presentation.meal.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealUi(
    val mealId: String,
    val mealName: String,
    val mealImage: String
): Parcelable
