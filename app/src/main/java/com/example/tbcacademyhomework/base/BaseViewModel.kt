package com.example.tbcacademyhomework.base

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.network.models.NetworkError
import com.example.tbcacademyhomework.network.parseError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {

    private val _errorChannel = Channel<NetworkError>()
    val errorFlow = _errorChannel.receiveAsFlow()


    fun <T> execute(call: suspend () -> Response<T>): Flow<T> {
        return channelFlow {
            try {
                val result = call()
                if (result.isSuccessful) {
                    result.body()?.let {
                        send(it)
                    }
                } else {
                    val error = result.parseError()
                    error?.let {
                        _errorChannel.send(error)
                    }
                }

            } catch (e: Exception) {
                e.message?.let {
                    _errorChannel.send(NetworkError(null, it))
                }

            }

        }
    }

}