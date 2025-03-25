package com.example.tbcacademyhomework.data.di

import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseStorageModule {

    @Singleton
    @Provides
    fun providesFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
}