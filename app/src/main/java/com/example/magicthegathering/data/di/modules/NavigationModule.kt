package com.example.magicthegathering.data.di.modules

import com.example.magicthegathering.data.implementations.NavigationRepositoryImpl
import com.example.magicthegathering.domain.repositories.NavigationRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigationRepositoryImpl(navigationRepositoryImpl: NavigationRepositoryImpl): NavigationRepository
}