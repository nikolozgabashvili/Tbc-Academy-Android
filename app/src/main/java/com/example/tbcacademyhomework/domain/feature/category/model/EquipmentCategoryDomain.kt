package com.example.tbcacademyhomework.domain.feature.category.model

data class EquipmentCategoryDomain(
    val id: String,
    val name: String,
    val germanName: String,
    val depth: Int,
    val children: List<EquipmentCategoryDomain>
)