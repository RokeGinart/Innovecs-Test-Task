package com.example.magicthegathering.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.magicthegathering.App
import com.example.magicthegathering.R
import com.example.magicthegathering.data.di.AppComponent
import com.example.magicthegathering.data.di.CustomViewModelFactory
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity() {

    lateinit var appComponent : AppComponent

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as App).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.setFragmentManager(supportFragmentManager)
    }

    override fun onDestroy() {
        viewModel.detachFragmentManager()
        super.onDestroy()
    }
}