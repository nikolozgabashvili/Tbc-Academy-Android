package com.example.tbcacademyhomework.data.category.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EquipmentCategoryDTO(
    val id: String,
    val name: String,
    @SerialName("name_de")
    val germanName: String,
    val children: List<EquipmentCategoryDTO>
)