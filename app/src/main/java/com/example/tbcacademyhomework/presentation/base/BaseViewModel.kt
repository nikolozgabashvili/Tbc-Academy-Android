package com.example.tbcacademyhomework.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE, ACTION, EVENT>(
    initialState: STATE,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event = Channel<EVENT>()
    val event = _event.receiveAsFlow()


    abstract fun onAction(action: ACTION)

    suspend fun sendEvent(event: EVENT) {
        _event.send(event)
    }


    fun updateState(reducer: STATE.() -> STATE) {
        _state.update(reducer)
    }


}