package com.example.magicthegathering

import android.app.Application
import com.example.magicthegathering.data.di.AppComponent
import com.example.magicthegathering.data.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}