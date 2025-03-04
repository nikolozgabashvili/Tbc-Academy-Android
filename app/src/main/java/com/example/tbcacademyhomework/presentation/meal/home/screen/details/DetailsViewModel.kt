package com.example.tbcacademyhomework.presentation.meal.home.screen.details

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isError
import com.example.tbcacademyhomework.domain.core.util.isLoading
import com.example.tbcacademyhomework.domain.meal.usecase.AddFavouriteUseCase
import com.example.tbcacademyhomework.domain.meal.usecase.GetMealDetailsByIdUseCase
import com.example.tbcacademyhomework.domain.meal.usecase.IsMealFavouriteUseCase
import com.example.tbcacademyhomework.domain.meal.usecase.RemoveFavouriteUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.meal.home.model.toDomain
import com.example.tbcacademyhomework.presentation.meal.home.screen.details.model.toUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DetailsViewModel @AssistedInject constructor(
    @Assisted("mealId") private val mealId: String,
    private val getMealDetailsByIdUseCase: GetMealDetailsByIdUseCase,
    private val isMealFavouriteUseCase: IsMealFavouriteUseCase,
    private val addMealFavouriteUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()


    init {
        isMealFavourite()
        getDetails()
    }

    private fun addFavourite() {
        launchCoroutineScope {
            val meal = _state.value.details
            meal?.let {
                addMealFavouriteUseCase(it.toDomain())
            }
        }
    }

    private fun removeFavourite() {
        launchCoroutineScope {
            val meal = _state.value.details
            meal?.let {
                removeFavouriteUseCase(mealId)
            }
        }
    }

    private fun isMealFavourite() {
        launchCoroutineScope {
            isMealFavouriteUseCase(mealId).collect { isFavourite ->
                _state.update {
                    it.copy(isFavourite = isFavourite)
                }
            }
        }
    }

    fun getDetails() {
        launchCoroutineScope {
            getMealDetailsByIdUseCase(mealId).collect { resource ->
                _state.update {
                    it.copy(
                        loading = resource.isLoading(),
                        isError = resource.isError()
                    )
                }
                when (resource) {
                    is Resource.Success -> {
                        val data = resource.data.toUi()
                        _state.update { it.copy(details = data) }
                    }

                    is Resource.Error -> Unit

                    Resource.Loading -> Unit
                }

            }
        }
    }

    fun favouriteClick() {
        if (_state.value.isFavourite) {
            removeFavourite()
        } else {
            addFavourite()
        }

    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("mealId") mealId: String,
        ): DetailsViewModel
    }

}