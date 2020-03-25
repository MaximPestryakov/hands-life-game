package com.github.maximpestryakov.hands.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.github.maximpestryakov.hands.db.AppDatabase
import com.github.maximpestryakov.hands.db.CellEntityDao
import com.github.maximpestryakov.hands.utils.DbLock

interface DbModule {

    val dbLock: DbLock

    val cellEntityDao: CellEntityDao
}

fun DbModule(application: Application): DbModule {
    return object : DbModule {

        val context: Context = application

        val appDatabase: AppDatabase by lazy {
            Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
                .fallbackToDestructiveMigrationOnDowngrade()
                .build()
        }

        override val dbLock: DbLock by lazy { DbLock(appDatabase) }

        override val cellEntityDao: CellEntityDao
            get() = appDatabase.cellEntityDao
    }
}
