package com.example.tbcacademyhomework.presentation.screen.category.util

import com.example.tbcacademyhomework.domain.feature.category.model.EquipmentCategoryDomain
import com.example.tbcacademyhomework.presentation.screen.category.model.Category
import com.example.tbcacademyhomework.presentation.screen.category.model.DepthIndicator

fun List<EquipmentCategoryDomain>.toUi(): List<Category> {
    val result = mutableListOf<Category>()

    fun flattenCategories(category: EquipmentCategoryDomain) {
        result.add(Category(
            id = category.id,
            name = category.name,
            depthItems = List(category.depth){DepthIndicator()}
        ))

        category.children.forEach { child ->
            flattenCategories(child)
        }
    }

    this.forEach { category ->
        flattenCategories(category)
    }

    return result
}

