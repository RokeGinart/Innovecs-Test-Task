package com.example.innovecstesttask.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innovecstesttask.data.di.CustomViewModelFactory
import com.example.innovecstesttask.data.di.ViewModelKey
import com.example.innovecstesttask.presentation.activity.MainViewModel
import com.example.innovecstesttask.presentation.fragment.start_screen.StartScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartScreenViewModel::class)
    protected abstract fun bindStartScreenViewModel(startScreenViewModel: StartScreenViewModel): ViewModel
}