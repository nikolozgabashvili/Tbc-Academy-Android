package com.example.tbcacademyhomework.presentation.map.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.usecases.GetLocationUseCase
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.isLoading
import com.example.tbcacademyhomework.presentation.core.util.toGenericString
import com.example.tbcacademyhomework.presentation.map.MapScreenEvent
import com.example.tbcacademyhomework.presentation.map.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MapScreenState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<MapScreenEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase().collect { resource ->
                _state.update { it.copy(loading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {
                        _state.update { it.copy(locations = resource.data.map { location -> location.toUi() }) }

                    }

                    is Resource.Error -> {
                        eventChannel.send(MapScreenEvent.Error(resource.error.toGenericString()))

                    }

                    Resource.Loading -> Unit
                }

            }
        }
    }


    fun locationPermissionEnabled(enabled:Boolean) {
        _state.update { it.copy(hasLocationPermission = enabled) }
    }

    fun gpsStatusChanged(enabled:Boolean){
        _state.update { it.copy(hasLocationEnabled = enabled) }
    }
}