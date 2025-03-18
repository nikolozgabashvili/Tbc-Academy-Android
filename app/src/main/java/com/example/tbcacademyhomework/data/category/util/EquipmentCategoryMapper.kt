package com.example.tbcacademyhomework.data.category.util

import com.example.tbcacademyhomework.data.category.model.EquipmentCategoryDTO
import com.example.tbcacademyhomework.domain.feature.category.model.EquipmentCategoryDomain

fun List<EquipmentCategoryDTO>.toDomain(): List<EquipmentCategoryDomain> {
    return this.map { it.toDomain() }
}

private fun EquipmentCategoryDTO.toDomain(): EquipmentCategoryDomain {
    return this.toDomainWithDepth()
}

private fun EquipmentCategoryDTO.toDomainWithDepth(depth: Int = 0): EquipmentCategoryDomain {
    return EquipmentCategoryDomain(
        id = id,
        name = name,
        germanName = germanName,
        depth = depth,
        children = children.map { it.toDomainWithDepth(depth + 1) }
    )
}