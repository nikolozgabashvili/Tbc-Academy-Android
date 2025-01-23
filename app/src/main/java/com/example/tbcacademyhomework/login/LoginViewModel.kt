package com.example.tbcacademyhomework.login


import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.network.RetrofitImpl
import com.example.tbcacademyhomework.network.model.AuthParams
import com.example.tbcacademyhomework.network.model.ResponseDto
import com.example.tbcacademyhomework.storage.DatastoreImpl
import com.example.tbcacademyhomework.util.Resource
import com.example.tbcacademyhomework.util.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val datastoreImpl: DatastoreImpl) : BaseViewModel() {


    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _responseChannel = Channel<Resource<ResponseDto>>()
    val responseFlow = _responseChannel.receiveAsFlow()

    private val _navigationChannel = Channel<Boolean>()
    val navigationFlow = _navigationChannel.receiveAsFlow()


    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = _loginScreenState.value.userEmail
            val password = _loginScreenState.value.userPassword
            execute {
                RetrofitImpl.authApiService.loginUser(AuthParams(email, password))
            }.collect { resource ->
                if (resource.isSuccess()) {
                    if (_loginScreenState.value.checkboxChecked) {
                        resource.data?.token?.let { token ->
                            datastoreImpl.saveToken(token)
                            datastoreImpl.saveEmail(email)
                        }
                    } else {
                        _navigationChannel.send(true)
                    }
                }
                _responseChannel.send(resource)
                _loginScreenState.update { it.copy(authResource = resource) }
            }
        }
    }

    fun userMail() = datastoreImpl.getMail()

    fun setUserEmail(email: String) {
        _loginScreenState.update { it.copy(userEmail = email) }

    }

    fun setUserPassword(password: String) {
        _loginScreenState.update { it.copy(userPassword = password) }
    }

    fun setCheckboxState(checked: Boolean) {
        _loginScreenState.update { it.copy(checkboxChecked = checked) }

    }


}