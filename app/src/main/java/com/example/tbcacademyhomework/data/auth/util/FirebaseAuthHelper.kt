package com.example.tbcacademyhomework.data.auth.util

import com.example.tbcacademyhomework.domain.auth.util.FirebaseError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FirebaseAuthHelper @Inject constructor() {

    fun safeCall(
        isRegistering: Boolean = false,
        call: suspend () -> AuthResult
    ): Flow<Resource<Unit, FirebaseError>> {
        return flow {
            emit(Resource.Loading)
            try {
                call()
                emit(Resource.Success(Unit))
            } catch (e: Exception) {
                emit(Resource.Error(getFirebaseError(e, isRegistering)))

            }
        }
    }

    fun safeCall(
        call: () -> Unit
    ): Flow<Resource<Unit, FirebaseError>> {
        return flow {
            emit(Resource.Loading)
            try {
                call()
                emit(Resource.Success(Unit))
            } catch (e: Exception) {
                emit(Resource.Error(getFirebaseError(e)))
            }
        }
    }
}

fun getFirebaseError(
    exception: Exception,
    isRegistering: Boolean = false
): FirebaseError {
    return when (exception) {
        is CancellationException -> {
            throw exception
        }

        is FirebaseAuthInvalidCredentialsException -> {
            if (isRegistering) {
                FirebaseError.INVALID_EMAIL
            } else {
                FirebaseError.INVALID_CREDENTIALS
            }
        }

        is FirebaseNetworkException -> {
            FirebaseError.NETWORK_ERROR
        }

        is FirebaseAuthUserCollisionException -> {
            FirebaseError.USER_COLLISION
        }

        else -> {
            FirebaseError.UNKNOWN
        }
    }
}