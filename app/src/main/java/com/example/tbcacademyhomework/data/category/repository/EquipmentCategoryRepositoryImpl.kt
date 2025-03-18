package com.example.tbcacademyhomework.data.category.repository

import com.example.tbcacademyhomework.data.category.api_service.EquipmentCategoryApiService
import com.example.tbcacademyhomework.data.category.util.toDomain
import com.example.tbcacademyhomework.data.core.util.ApiHelper
import com.example.tbcacademyhomework.domain.core.util.NetworkError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.mapResource
import com.example.tbcacademyhomework.domain.feature.category.model.EquipmentCategoryDomain
import com.example.tbcacademyhomework.domain.feature.category.repository.EquipmentCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EquipmentCategoryRepositoryImpl @Inject constructor(
    private val apiService: EquipmentCategoryApiService,
    private val apiHelper: ApiHelper
) : EquipmentCategoryRepository {
    override suspend fun getEquipment(): Flow<Resource<List<EquipmentCategoryDomain>, NetworkError>> {
        return apiHelper.safeCall {
            apiService.fetchCategories()
        }.mapResource {
            it.toDomain()
        }

    }
}