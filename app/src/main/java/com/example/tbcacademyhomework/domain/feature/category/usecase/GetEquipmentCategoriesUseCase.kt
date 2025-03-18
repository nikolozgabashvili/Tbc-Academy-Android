package com.example.tbcacademyhomework.domain.feature.category.usecase

import com.example.tbcacademyhomework.domain.core.util.NetworkError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isSuccess
import com.example.tbcacademyhomework.domain.feature.category.model.EquipmentCategoryDomain
import com.example.tbcacademyhomework.domain.feature.category.repository.EquipmentCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetEquipmentCategoriesUseCase {
    suspend operator fun invoke(query: String = ""): Flow<Resource<List<EquipmentCategoryDomain>, NetworkError>>
}

class GetEquipmentCategoriesUseCaseImpl @Inject constructor(
    private val repository: EquipmentCategoryRepository
) : GetEquipmentCategoriesUseCase {

    override suspend fun invoke(query: String): Flow<Resource<List<EquipmentCategoryDomain>, NetworkError>> {
        return repository.getEquipment().map { data ->
            if (query.isEmpty() || !data.isSuccess()) {
                data
            } else if (data is Resource.Success) {
                val filteredItems = filterCategories(categories = data.data, query)
                Resource.Success(filteredItems)
            } else {
                data
            }
        }
    }
}

private suspend fun filterCategories(
    categories: List<EquipmentCategoryDomain>,
    query: String
): List<EquipmentCategoryDomain> {

    return withContext(Dispatchers.Default) {
        val result = mutableListOf<EquipmentCategoryDomain>()
        for (category in categories) {
            val matches = category.name
                .contains(other = query, ignoreCase = true)
            val filteredChildren = filterCategories(category.children, query)
            if (matches && filteredChildren.isNotEmpty()) {
                result.add(
                    category.copy(children = filteredChildren)
                )
            } else if (matches) {
                result.add(category)
            } else if (filteredChildren.isNotEmpty()) {
                result.addAll(filteredChildren)
            }
        }
        result
    }

}

