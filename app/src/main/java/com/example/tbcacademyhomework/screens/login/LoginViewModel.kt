package com.example.tbcacademyhomework.screens.login

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.models.AuthParams
import com.example.tbcacademyhomework.network.models.Resource
import com.example.tbcacademyhomework.network.models.toLoginResponse
import com.example.tbcacademyhomework.network.retrofit.RetrofitInstance
import com.example.tbcacademyhomework.screens.login.model.LoginResponse
import com.example.tbcacademyhomework.util.UserDataValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private val userDataValidator = UserDataValidator()

    private val _loginResponse = MutableStateFlow<Resource<LoginResponse?>?>(null)
    val loginResponse = _loginResponse.asStateFlow()


    fun checkEmail(email: String) = userDataValidator.validateEmail(email)

    fun checkPassword(password: String) = userDataValidator.validatePassword(password)

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            execute {
                RetrofitInstance.authService.loginUser(AuthParams(email, password))
            }.collect { it ->
                _loginResponse.emit(it.mapResource { it?.toLoginResponse() })
            }
        }
    }
}