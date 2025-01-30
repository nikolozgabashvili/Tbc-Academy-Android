package com.example.tbcacademyhomework.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.bcacademyhomework.storage.User
import kotlinx.coroutines.flow.first

class UserDataStoreImpl(private val context: Context) {

    private val userDataStore: DataStore<User> = DataStoreFactory.create(
        serializer = UserSerializer,
        produceFile = { context.filesDir.resolve("user_db.pb") }
    )

    suspend fun saveUser(user: User) {
        userDataStore.updateData { currentUser ->
            currentUser.toBuilder().mergeFrom(user).build()
        }
    }

    suspend fun getUser(): User {
        return userDataStore.data.first()
    }


}