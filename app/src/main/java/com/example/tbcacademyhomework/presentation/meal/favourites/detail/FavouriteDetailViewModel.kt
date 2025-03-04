package com.example.tbcacademyhomework.presentation.meal.favourites.detail

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.meal.usecase.AddFavouriteUseCase
import com.example.tbcacademyhomework.domain.meal.usecase.GetLocalMealByIdUseCase
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


class FavouriteDetailViewModel @AssistedInject constructor(
    @Assisted("mealId") private val mealId: String,
    private val getMealDetailsByIdUseCase: GetLocalMealByIdUseCase,
    private val isMealFavouriteUseCase: IsMealFavouriteUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavouriteDetailsScreenState())
    val state = _state.asStateFlow()

    init {
        launchCoroutineScope {
            _state.update {
                it.copy(
                    details = getMealDetailsByIdUseCase(mealId)?.toUi(),
                )
            }
        }

        launchCoroutineScope {
            isMealFavouriteUseCase(mealId).collect { isFavourite ->
                _state.update {
                    it.copy(
                        isFavourite = isFavourite
                    )
                }
            }
        }
    }

    private fun addFavourite() {
        launchCoroutineScope {
            val meal = _state.value.details?.toDomain()
            meal?.let {
                addFavouriteUseCase(it)
            }
        }
    }

    private fun removeFavourite() {
        launchCoroutineScope {
            removeFavouriteUseCase(mealId)

        }
    }

    fun onFavouriteClick() {
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
        ): FavouriteDetailViewModel
    }
}