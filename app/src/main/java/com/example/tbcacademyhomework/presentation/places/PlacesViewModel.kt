package com.example.tbcacademyhomework.presentation.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.models.Place
import com.example.tbcacademyhomework.domain.repository.PlacesRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placesRepository: PlacesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<Place>, DataError>>(Resource.Loading)
    val state = _state.asStateFlow()

    private val eventChannel = Channel<DataError>()
    val event = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            placesRepository.getPlaces().collect {
                _state.value = it
                if (it is Resource.Error) {
                    eventChannel.send(it.error)
                }

            }

        }
    }

}