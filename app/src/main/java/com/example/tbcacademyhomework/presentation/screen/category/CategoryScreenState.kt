package com.example.tbcacademyhomework.presentation.screen.category

import com.example.tbcacademyhomework.presentation.core.util.GenericString
import com.example.tbcacademyhomework.presentation.screen.category.model.Category

data class CategoryScreenState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: GenericString? = null
) {
    val showList = error == null
}