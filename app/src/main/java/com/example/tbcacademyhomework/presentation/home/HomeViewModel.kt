package com.example.tbcacademyhomework.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.usecases.GetPostsUseCase
import com.example.tbcacademyhomework.domain.usecases.GetStoriesUseCase
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.isLoading
import com.example.tbcacademyhomework.presentation.util.ScreenEvent
import com.example.tbcacademyhomework.presentation.util.ext.toGenericString
import com.example.tbcacademyhomework.presentation.util.ext.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val eventChannel =
        Channel<ScreenEvent>(onBufferOverflow = BufferOverflow.DROP_OLDEST, capacity = 1)
    val event = eventChannel.receiveAsFlow()

    init {
        getPosts()
        getStories()

    }

    private fun getPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect { resource ->
                _state.update { it.copy(isPostsLoading = resource.isLoading()) }

                if (resource is Resource.Success) {
                    _state.update { it.copy(posts = resource.data.map { story -> story.toUi() }) }

                } else if (resource is Resource.Error) {
                    val error = resource.error.toGenericString()
                    eventChannel.send(ScreenEvent.Error(error))

                }

            }
        }
    }

    private fun getStories() {
        viewModelScope.launch {
            getStoriesUseCase().collect { resource ->
                _state.update { it.copy(isStoriesLoading = resource.isLoading()) }

                if (resource is Resource.Success) {
                    _state.update { it.copy(stories = resource.data.map { story -> story.toUi() }) }

                } else if (resource is Resource.Error) {
                    val error = resource.error.toGenericString()
                    eventChannel.send(ScreenEvent.Error(error))

                }

            }
        }
    }
}