package com.example.tbcacademyhomework.presentation.meal.home.screen.home

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isError
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
        getCategories()
    }

    fun getCategories() {
        getMeals()
        launchCoroutineScope {
            getMealCategoryUseCase().collect { resource ->
                _state.update {
                    it.copy(
                        categoryLoading = resource.isLoading(),
                        mealLoading = resource.isLoading(),
                        mealError = false,
                        categoryError = resource.isError()
                    )
                }

                if (resource is Resource.Success) {
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

            }

        }
    }

    fun retryMeals() {
        if (_state.value.mappedCategories.isEmpty()) {
            getCategories()
        } else {
            getMeals()
        }
    }

    private fun getMeals() {
        launchCoroutineScope {
            val category = _state.value.mappedCategories.firstOrNull { it.isSelected }?.name
            category?.let {
                getMealByCategoryUseCase(category).collect { resource ->
                    _state.update {
                        it.copy(
                            mealLoading = resource.isLoading(),
                            mealError = resource.isError()
                        )
                    }
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
        }
    }

    fun onCategorySelected(category: CategoryUi) {
        _state.update { it.copy(selectedCategoryId = category.id) }
        shouldMakeCall()

    }

    private fun shouldMakeCall() {
        if (_state.value.mealsToShow.isEmpty()) {
            getMeals()
        } else {
            _state.update { it.copy(mealLoading = false, mealError = false) }
        }
    }
}