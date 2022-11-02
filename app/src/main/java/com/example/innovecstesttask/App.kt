package com.example.innovecstesttask

import android.app.Application
import com.example.innovecstesttask.data.di.AppComponent
import com.example.innovecstesttask.data.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}