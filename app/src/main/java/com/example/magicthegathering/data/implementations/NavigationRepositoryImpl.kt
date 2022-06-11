package com.example.magicthegathering.data.implementations

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.magicthegathering.R
import com.example.magicthegathering.domain.repositories.NavigationRepository
import com.example.magicthegathering.presentation.fragment.main_screen.MainScreenFragment
import javax.inject.Inject


class NavigationRepositoryImpl @Inject constructor() : NavigationRepository {

    private var fragmentManager : FragmentManager? = null

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    override fun detachFragmentManager() {
        fragmentManager = null
    }

    override fun navigateToStartScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToMainScreen() {
        navigateTo(MainScreenFragment())
    }

    override fun navigateToCardDetailsScreen(id: Int) {
        TODO("Not yet implemented")
    }

    private fun navigateTo(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        type: NavType = NavType.ADD,
    ) {
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()

        when (type) {
            NavType.ADD -> transaction.add(R.id.container, fragment)
            NavType.REPLACE -> transaction.replace(R.id.container, fragment)
        }

        if(addToBackStack) transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    internal enum class NavType {
        ADD, REPLACE
    }
}