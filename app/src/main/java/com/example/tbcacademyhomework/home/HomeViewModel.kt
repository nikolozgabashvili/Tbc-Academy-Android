package com.example.tbcacademyhomework.home

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.storage.DatastoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val datastoreImpl: DatastoreImpl) : BaseViewModel() {

    fun getUserMail() = datastoreImpl.getMail()

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            datastoreImpl.clearMemory()
        }
    }



}