package com.example.tbcacademyhomework.presentation.meal.favourites

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.meal.usecase.GetLocalMealsUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.meal.home.screen.details.model.toMealUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteUseCase: GetLocalMealsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavouriteScreenState())
    val state = _state.asStateFlow()

    init {
        launchCoroutineScope {
            getFavouriteUseCase().collect { favourites ->
                _state.update {
                    it.copy(
                        favourites = favourites.map {
                            it.toMealUi()
                        }
                    )
                }
            }
        }
    }
}