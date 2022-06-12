package com.example.magicthegathering.data.di.modules

import com.example.magicthegathering.data.implementations.ApiRepositoryImpl
import com.example.magicthegathering.domain.repositories.ApiRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindApiServiceImpl(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository

}