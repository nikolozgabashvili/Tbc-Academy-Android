package com.example.tbcacademyhomework.screens.register

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.models.AuthParams
import com.example.tbcacademyhomework.network.models.Resource
import com.example.tbcacademyhomework.network.models.toRegisterResponse
import com.example.tbcacademyhomework.network.retrofit.RetrofitInstance
import com.example.tbcacademyhomework.screens.register.model.RegisterResponse
import com.example.tbcacademyhomework.util.UserDataValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {

    private val _registerResponse = MutableStateFlow<Resource<RegisterResponse?>?>(null)
    val registerResponse = _registerResponse.asStateFlow()

    private val userDataValidator = UserDataValidator()


    fun checkEmail(email: String) = userDataValidator.validateEmail(email)
    fun checkPassword(password: String) = userDataValidator.validatePassword(password)

    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            execute {
                RetrofitInstance.authService.registerUser(AuthParams(email, password))
            }.collect { it ->
                _registerResponse.emit(it.mapResource { it?.toRegisterResponse() })
            }
        }
    }
}