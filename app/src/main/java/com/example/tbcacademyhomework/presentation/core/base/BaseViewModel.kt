package com.example.tbcacademyhomework.presentation.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

/**
 * Base ViewModel class to manage UI state and events.
 */

abstract class BaseViewModel<STATE, ACTION, EVENT>(
    initialState: STATE
) : ViewModel() {

    /**
     * Holds the current UI state.
     */
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    /**
     * Channel used for sending one-time Events.
     */
    private val eventChannel = Channel<EVENT>()
    val events = eventChannel.receiveAsFlow()

    /** handles User actions from controller */
    abstract fun onAction(action: ACTION)

    fun updateState(reducer: STATE.() -> STATE) {
        _state.update(reducer)
    }

    suspend fun sendEvent(event: EVENT) {
        eventChannel.send(event)
    }
}