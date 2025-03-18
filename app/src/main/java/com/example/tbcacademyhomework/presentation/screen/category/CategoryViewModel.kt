package com.example.tbcacademyhomework.presentation.screen.category

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.isLoading
import com.example.tbcacademyhomework.domain.feature.category.usecase.GetEquipmentCategoriesUseCase
import com.example.tbcacademyhomework.presentation.core.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.core.util.toGenericString
import com.example.tbcacademyhomework.presentation.screen.category.util.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getEquipmentCategoriesUseCase: GetEquipmentCategoriesUseCase
) : BaseViewModel<CategoryScreenState, CategoryScreenAction, Nothing>(CategoryScreenState()) {

    private val searchQuery = MutableStateFlow("")

    init {
        initSearchObserver()
    }


    override fun onAction(action: CategoryScreenAction) {
        when (action) {
            is CategoryScreenAction.OnQueryChange -> search(action.query)
            CategoryScreenAction.OnRetry -> fetchData()
        }

    }

    private fun fetchData() {
        getData(searchQuery.value)
    }

    @OptIn(FlowPreview::class)
    private fun initSearchObserver() {
        viewModelScope.launch {
            searchQuery.debounce(300).collect {
                getData(query = it)
            }
        }
    }

    private fun search(query: String) {
        searchQuery.value = query
    }

    private fun getData(query: String) {
        viewModelScope.launch {
            getEquipmentCategoriesUseCase(query).collect { resource ->
                updateState { copy(isLoading = resource.isLoading(), error = null) }
                if (resource is Resource.Success) {
                    updateState {
                        copy(
                            categories = resource.data.toUi(),
                        )
                    }
                } else if (resource is Resource.Error) {
                    updateState {
                        copy(
                            error = resource.error.toGenericString(),
                            categories = emptyList()
                        )
                    }
                }
            }
        }
    }
}