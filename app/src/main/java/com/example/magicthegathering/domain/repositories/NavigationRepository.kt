package com.example.magicthegathering.domain.repositories

import androidx.fragment.app.FragmentManager

interface NavigationRepository {
    fun attachFragmentManager(fragmentManager: FragmentManager)
    fun detachFragmentManager()

    fun navigateToStartScreen()
    fun navigateToMainScreen()
    fun navigateToCardDetailsScreen(id : Int)
}