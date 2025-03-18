package com.example.tbcacademyhomework.presentation.screen.category

sealed interface CategoryScreenAction {
    data class OnQueryChange(val query: String) : CategoryScreenAction
    data object OnRetry : CategoryScreenAction
}