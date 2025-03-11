package com.example.tbcacademyhomework.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.tbcacademyhomework.data.datastore.DatastoreManagerImpl
import com.example.tbcacademyhomework.domain.datastore.DatastoreManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatastoreModule {


    @Binds
    @Singleton
    abstract fun bindDatastoreManager(impl: DatastoreManagerImpl): DatastoreManager


    companion object {
        @Provides
        @Singleton
        fun providesDatastore(
            @ApplicationContext context: Context
        ): DataStore<Preferences> {
            return context.datastore
        }
    }
}

private val Context.datastore by preferencesDataStore("datastore_preferences")