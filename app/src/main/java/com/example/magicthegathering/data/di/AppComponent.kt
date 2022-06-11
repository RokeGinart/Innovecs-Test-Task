package com.example.magicthegathering.data.di

import com.example.magicthegathering.data.di.modules.NetworkModule
import com.example.magicthegathering.data.di.modules.RepositoryModule
import com.example.magicthegathering.data.di.modules.ViewModelModule
import com.example.magicthegathering.presentation.activity.MainActivity
import com.example.magicthegathering.presentation.fragment.main_screen.MainScreenFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MainScreenFragment)
}