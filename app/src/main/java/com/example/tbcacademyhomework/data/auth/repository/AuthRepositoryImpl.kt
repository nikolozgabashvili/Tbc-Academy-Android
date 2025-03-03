package com.example.tbcacademyhomework.data.auth.repository

import com.example.tbcacademyhomework.data.auth.util.FirebaseAuthHelper
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.auth.util.FirebaseError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseAuthHelper: FirebaseAuthHelper
) : AuthRepository {

    override val isUserLoggedIn: Boolean
        get() = firebaseAuth.currentUser != null

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<Unit, FirebaseError>> {
        return firebaseAuthHelper.safeCall(isRegistering = false) {
            firebaseAuth.signInWithEmailAndPassword(
                email, password
            ).await()
        }


    }

    override suspend fun register(
        email: String,
        password: String
    ): Flow<Resource<Unit, FirebaseError>> {
        return firebaseAuthHelper.safeCall(isRegistering = true) {
            firebaseAuth.createUserWithEmailAndPassword(
                email, password
            ).await()
        }

    }

    override suspend fun logout(): Flow<Resource<Unit, FirebaseError>> {
        return firebaseAuthHelper.safeCall(
            call = firebaseAuth::signOut
        )
    }

}