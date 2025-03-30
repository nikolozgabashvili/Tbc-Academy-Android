package com.example.tbcacademyhomework.data.di

import com.example.tbcacademyhomework.data.repository.AccountRepositoryImpl
import com.example.tbcacademyhomework.data.repository.ExchangeRepositoryImpl
import com.example.tbcacademyhomework.data.repository.TransactionRepositoryImpl
import com.example.tbcacademyhomework.domain.repository.AccountRepository
import com.example.tbcacademyhomework.domain.repository.ExchangeRepository
import com.example.tbcacademyhomework.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindExchangeRateRepository(impl: ExchangeRepositoryImpl): ExchangeRepository


}
