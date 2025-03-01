package com.example.tbcacademyhomework.presentation.core.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchCoroutineScope(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Unit
) {
    viewModelScope.launch(dispatcher) {
        block()
    }

}

fun Fragment.launchCoroutineScope(
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend () -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(dispatcher) {
        repeatOnLifecycle(state) {
            block()
        }
    }
}