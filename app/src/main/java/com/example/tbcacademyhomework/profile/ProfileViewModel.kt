package com.example.tbcacademyhomework.profile

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.storage.DatastoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val datastoreImpl: DatastoreImpl) : BaseViewModel() {

    private val _logOutChannel = Channel<Unit>()
    val logOutFlow = _logOutChannel.receiveAsFlow()

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            datastoreImpl.clearMemory()
            _logOutChannel.send(Unit)

        }
    }


}