package com.example.tbcacademyhomework.product

import androidx.annotation.DrawableRes
import com.example.tbcacademyhomework.category.CategoryType

data class Product(
    @DrawableRes val image: Int,
    val title: String,
    val price: String,
    val categoryType: CategoryType
)
