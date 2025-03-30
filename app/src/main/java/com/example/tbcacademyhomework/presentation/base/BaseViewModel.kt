package com.example.tbcacademyhomework.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<ACTION, EVENT, STATE>(
    initialState: STATE
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val eventChannel = Channel<EVENT>()
    val event = eventChannel.receiveAsFlow()

    abstract fun onAction(action: ACTION)

    protected suspend fun sendEvent(event: EVENT) {
        eventChannel.send(event)
    }

    protected fun updateState(reducer: STATE.() -> STATE) {
        _state.update(reducer)
    }
}