package com.example.innovecstesttask.data.di.modules

import com.example.innovecstesttask.data.implementations.ApiRepositoryImpl
import com.example.innovecstesttask.domain.repositories.ApiRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindApiServiceImpl(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository
}