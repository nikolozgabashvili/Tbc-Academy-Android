package com.example.tbcacademyhomework.data.local.database.di

import android.content.Context
import androidx.room.Room
import com.example.tbcacademyhomework.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideMealDao(
        database: AppDatabase
    ) = database.mealDao()
}