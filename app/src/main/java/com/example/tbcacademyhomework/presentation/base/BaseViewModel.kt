package com.example.tbcacademyhomework.presentation.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<STATE, ACTION, EVENT>(
    initialState: STATE
) : ViewModel() {

    var state by mutableStateOf(initialState)
        private set

    private val _event = Channel<EVENT>()
    val event = _event.receiveAsFlow()


    abstract fun onAction(action: ACTION)

    suspend fun sendEvent(event: EVENT) {
        _event.send(event)
    }


    fun updateState(reducer: STATE.() -> STATE) {
        state = reducer(state)
    }


}