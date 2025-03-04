package com.example.tbcacademyhomework.presentation.core.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactoryProvider {
    @Suppress("UNCHECKED_CAST")
    fun <VM : ViewModel> provideViewModel(predicate: () -> VM): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return predicate.invoke() as T
            }
        }
}