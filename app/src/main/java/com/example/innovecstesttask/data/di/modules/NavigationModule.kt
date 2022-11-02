package com.example.innovecstesttask.data.di.modules

import com.example.innovecstesttask.data.implementations.NavigationRepositoryImpl
import com.example.innovecstesttask.domain.repositories.NavigationRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigationRepositoryImpl(navigationRepositoryImpl: NavigationRepositoryImpl): NavigationRepository
}