package com.example.tbcacademyhomework.home

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.RetrofitImpl
import com.example.tbcacademyhomework.util.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {


    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()


    init {
        fetchUsers()
    }


    fun fetchUsers() {
        val lastFetchedPage = _state.value.usersResource?.data?.page ?: 0
        viewModelScope.launch(Dispatchers.IO) {
            execute {
                RetrofitImpl.apiService.fetchUsers(lastFetchedPage + 1)
            }.collect { resource ->
                val newData = resource.data?.data?.map { it.toUser() } ?: emptyList()
                _state.update {
                    it.copy(
                        usersResource = resource,
                        usersData = it.usersData?.plus(newData)
                    )
                }

            }

        }

    }

}