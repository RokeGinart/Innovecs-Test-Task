package com.example.innovecstesttask.domain.repositories

import androidx.fragment.app.FragmentManager

interface NavigationRepository {
    var fragmentManager : FragmentManager?
    fun attachFragmentManager(fragmentManager: FragmentManager)
    fun detachFragmentManager()

    fun navigateToStartScreen()
}