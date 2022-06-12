package com.example.magicthegathering.data.di

import com.example.magicthegathering.data.di.modules.NavigationModule
import com.example.magicthegathering.data.di.modules.NetworkModule
import com.example.magicthegathering.data.di.modules.RepositoryModule
import com.example.magicthegathering.data.di.modules.ViewModelModule
import com.example.magicthegathering.presentation.activity.MainActivity
import com.example.magicthegathering.presentation.fragment.main_screen.MainScreenFragment
import com.example.magicthegathering.presentation.fragment.start_screen.StartScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    NavigationModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: StartScreenFragment)
    fun inject(fragment: MainScreenFragment)
}