package com.example.tbcacademyhomework.presentation.meal.home

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isLoading
import com.example.tbcacademyhomework.domain.meal.usecase.GetMealByCategoryUseCase
import com.example.tbcacademyhomework.domain.meal.usecase.GetMealCategoriesUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.meal.home.model.CategoryUi
import com.example.tbcacademyhomework.presentation.meal.home.model.MealByCategory
import com.example.tbcacademyhomework.presentation.meal.home.model.MealUi
import com.example.tbcacademyhomework.presentation.meal.home.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMealCategoryUseCase: GetMealCategoriesUseCase,
    private val getMealByCategoryUseCase: GetMealByCategoryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        launchCoroutineScope {
            getMealCategoryUseCase().collect { resource ->
                _state.update { it.copy(categoryLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Success -> {
                        val categories = resource.data.toUi()
                        _state.update {
                            it.copy(
                                rawCategories = categories,
                                rawMeals = categories.map { MealByCategory(it.id, it.name) },
                            )
                        }
                        categories.firstOrNull()?.let {
                            onCategorySelected(it)
                        }
                    }

                    is Resource.Error -> Unit//todo
                    Resource.Loading -> Unit
                }

            }

        }
    }

    fun onCategorySelected(category: CategoryUi) {
        _state.update { it.copy(selectedCategoryId = category.id) }
        shouldMakeCall(category.name)


    }

    private fun shouldMakeCall(category: String) {
        if (_state.value.mealsToShow.isEmpty()) {
            launchCoroutineScope {
                getMealByCategoryUseCase(category).collect { resource ->
                    _state.update { it.copy(mealLoading = resource.isLoading()) }
                    if (resource is Resource.Success) {
                        val mealList = resource.data
                        _state.update { state ->
                            state.copy(
                                rawMeals = state.rawMeals.map { mealByCategory ->
                                    if (mealByCategory.categoryName == category) {
                                        mealByCategory.copy(meals = mealList.meals.map { meal ->
                                            MealUi(
                                                mealId = meal.id,
                                                mealImage = meal.mealImage,
                                                mealName = meal.mealName
                                            )
                                        })
                                    } else mealByCategory
                                }
                            )
                        }
                    }
                }
            }
        } else {
            _state.update { it.copy(mealLoading = false) }
        }


    }
}