package com.example.tbcacademyhomework.data.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tbcacademyhomework.data.core.repository.UserPrefsRepositoryImpl
import com.example.tbcacademyhomework.domain.core.repository.UserPrefsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UserPrefsModule {

    @Binds
    abstract fun bindsUserPrefsRepository(impl: UserPrefsRepositoryImpl): UserPrefsRepository


    companion object {

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")

        @Provides
        fun providesSessionDatastore(
            @ApplicationContext context: Context
        ): DataStore<Preferences> {
            return context.dataStore
        }
    }


}