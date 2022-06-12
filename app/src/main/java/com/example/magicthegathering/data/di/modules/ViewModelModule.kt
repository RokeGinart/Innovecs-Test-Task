package com.example.magicthegathering.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.magicthegathering.data.di.CustomViewModelFactory
import com.example.magicthegathering.data.di.ViewModelKey
import com.example.magicthegathering.presentation.activity.MainViewModel
import com.example.magicthegathering.presentation.fragment.main_screen.MainScreenViewModel
import com.example.magicthegathering.presentation.fragment.start_screen.StartScreenViewModel
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
    @ViewModelKey(MainScreenViewModel::class)
    protected abstract fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartScreenViewModel::class)
    protected abstract fun bindStartScreenViewModel(startScreenViewModel: StartScreenViewModel): ViewModel
}