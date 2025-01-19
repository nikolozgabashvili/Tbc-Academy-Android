package com.example.tbcacademyhomework.screens.login

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.models.AuthParams
import com.example.tbcacademyhomework.network.models.toLoginResponse
import com.example.tbcacademyhomework.network.retrofit.RetrofitInstance
import com.example.tbcacademyhomework.screens.login.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse = _loginResponse.asStateFlow()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            execute {
                RetrofitInstance.authService.loginUser(AuthParams(email, password))
            }.collect {
                _loginResponse.emit(it.toLoginResponse())
            }
        }
    }
}