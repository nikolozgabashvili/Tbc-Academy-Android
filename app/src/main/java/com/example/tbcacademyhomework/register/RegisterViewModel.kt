package com.example.tbcacademyhomework.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.RetrofitImpl
import com.example.tbcacademyhomework.network.model.AuthParams
import com.example.tbcacademyhomework.network.model.ResponseDto
import com.example.tbcacademyhomework.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {


    private val _registerScreenState = MutableStateFlow(RegisterScreenState())
     val registerScreenState = _registerScreenState.asStateFlow()


    private val _responseChannel = Channel<Resource<ResponseDto>>()
    val responseFlow = _responseChannel.receiveAsFlow()


    fun setEmail(email: String) {
        _registerScreenState.update { it.copy(email = email) }
    }

    fun setPassword(password: String) {
        _registerScreenState.update { it.copy(password = password) }
    }

    fun setRepeatPassword(password: String) {
        _registerScreenState.update { it.copy(repeatPassword = password) }
    }

    fun registerUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = _registerScreenState.value.email
            val password = _registerScreenState.value.password
            execute {
                RetrofitImpl.authApiService.registerUser(AuthParams(email, password))
            }.collect { resource ->
                _responseChannel.send(resource)

            }
        }
    }
}