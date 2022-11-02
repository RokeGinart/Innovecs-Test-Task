package com.example.innovecstesttask.data.di

import com.example.innovecstesttask.data.di.modules.NavigationModule
import com.example.innovecstesttask.data.di.modules.NetworkModule
import com.example.innovecstesttask.data.di.modules.RepositoryModule
import com.example.innovecstesttask.data.di.modules.ViewModelModule
import com.example.innovecstesttask.presentation.activity.MainActivity
import com.example.innovecstesttask.presentation.fragment.start_screen.StartScreenFragment
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
}