package com.example.magicthegathering.presentation.activity

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.magicthegathering.domain.repositories.NavigationRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : ViewModel() {

    fun setFragmentManager(fragmentManager: FragmentManager){
        navigationRepository.attachFragmentManager(fragmentManager)
        navigateToStartScreen()
    }

    private fun navigateToStartScreen(){
        navigationRepository.navigateToStartScreen()
    }

    fun detachFragmentManager(){
        navigationRepository.detachFragmentManager()
    }

    override fun onCleared() {
        super.onCleared()
    }
}