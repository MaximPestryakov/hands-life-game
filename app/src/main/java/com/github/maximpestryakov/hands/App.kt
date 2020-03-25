package com.github.maximpestryakov.hands

import android.app.Activity
import android.app.Application
import com.github.maximpestryakov.hands.di.AppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent(this)
    }
}

val Activity.appComponent: AppComponent
    get() = (applicationContext as App).appComponent
