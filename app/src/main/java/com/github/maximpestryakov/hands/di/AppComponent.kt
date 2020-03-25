package com.github.maximpestryakov.hands.di

import android.app.Application

interface AppComponent : AppModule, DbModule

fun AppComponent(application: Application): AppComponent {
    val dbModule = DbModule(application)
    return object : AppComponent,
        AppModule by AppModule(dbModule),
        DbModule by dbModule {}
}
