package com.example.tbcacademyhomework.domain.feature.category.repository

import com.example.tbcacademyhomework.domain.core.util.NetworkError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.feature.category.model.EquipmentCategoryDomain
import kotlinx.coroutines.flow.Flow

interface EquipmentCategoryRepository {
    suspend fun getEquipment(): Flow<Resource<List<EquipmentCategoryDomain>, NetworkError>>
}