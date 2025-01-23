package com.example.tbcacademyhomework.base

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.network.model.NetworkError
import com.example.tbcacademyhomework.util.ErrorType
import com.example.tbcacademyhomework.util.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException

abstract class BaseViewModel : ViewModel() {


    suspend fun <T> execute(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = call()
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()))
                } else {
                    val error = parseError(response)
                    emit(Resource.Error(error))
                }

            } catch (e: UnresolvedAddressException) {
                e.printStackTrace()
                emit(Resource.Error(null, ErrorType.NO_INTERNET))
            } catch (e: SerializationException) {
                e.printStackTrace()
                emit(Resource.Error(null, ErrorType.SERIALIZATION_ERROR))
            } catch (e: UnknownHostException) {
                emit(Resource.Error(null, ErrorType.UNKNOWN_HOST))
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                e.printStackTrace()
                emit(Resource.Error(null, ErrorType.UNKNOWN_ERROR))
            }
        }

    }


    private fun parseError(response: Response<*>): String? {
        val errorString = response.errorBody()?.string()
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        return errorString?.let { json.decodeFromString<NetworkError>(it).error }
    }

}