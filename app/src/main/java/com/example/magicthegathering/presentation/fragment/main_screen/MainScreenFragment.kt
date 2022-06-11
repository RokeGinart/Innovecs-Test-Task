package com.example.magicthegathering.presentation.fragment.main_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.magicthegathering.R
import com.example.magicthegathering.data.di.CustomViewModelFactory
import com.example.magicthegathering.presentation.activity.MainActivity
import javax.inject.Inject


class MainScreenFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel : MainScreenViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }
}