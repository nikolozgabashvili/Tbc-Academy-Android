package com.example.tbcacademyhomework.data.local.database.converter

import com.example.tbcacademyhomework.data.local.database.model.MealEntity
import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain

fun MealDetailListDomain.MealDetailDomain.toEntity(): MealEntity {
    val ingredients = MapConverter.mapToString(this.ingredientMap)
    return MealEntity(
        id = this.id,
        name = this.name,
        category = this.category,
        area = this.area,
        instructions = this.instructions,
        image = this.image,
        youtubeVideo = this.youtubeVideo,
        ingredientMap = ingredients
    )
}

fun MealEntity.toDomain(): MealDetailListDomain.MealDetailDomain {
    val ingredients = MapConverter.stringToMap(this.ingredientMap)
    return MealDetailListDomain.MealDetailDomain(
        id = this.id,
        name = this.name,
        category = this.category,
        area = this.area,
        instructions = this.instructions,
        image = this.image,
        youtubeVideo = this.youtubeVideo,
        ingredientMap = ingredients ?: emptyMap()
    )
}