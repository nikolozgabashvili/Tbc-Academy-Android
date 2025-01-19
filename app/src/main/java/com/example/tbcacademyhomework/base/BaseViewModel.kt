package com.example.tbcacademyhomework.base

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.network.models.Resource
import com.example.tbcacademyhomework.network.parseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {


    inline fun <T> execute(crossinline call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            try {
                val result = call()
                if (result.isSuccessful) {
                    emit(Resource.Success(result.body()))

                } else {
                    val error = result.parseError()
                    emit(Resource.Error(error))
                }

            } catch (e: Exception) {
                e.message?.let {
                    emit(Resource.Error(error = "something went wrong"))
                }

            }
        }
    }

    inline fun <T, R> Resource<T>.mapResource(map: (T?) -> R?): Resource<R> {
        return when (this) {
            is Resource.Success -> Resource.Success(map(data))
            is Resource.Error -> Resource.Error(errorMessage)
        }
    }

}