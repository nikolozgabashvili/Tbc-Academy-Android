package com.example.tbcacademyhomework.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.datastore.DatastorePreferenceKeys
import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCase
import com.example.tbcacademyhomework.presentation.login.ClearDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getValueUseCase: GetValueUseCase,
    private val clearDataUseCase: ClearDataUseCase
) : ViewModel() {

    private val eventChannel = Channel<Unit>()
    val event = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getValueUseCase(DatastorePreferenceKeys.EMAIL, null).first()?.let { email ->
                _state.update { it.copy(email = email) }
            }

        }

    }


    fun logOutUser() {
        viewModelScope.launch {
            clearDataUseCase()
            eventChannel.send(Unit)
        }

    }

}