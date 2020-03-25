package com.github.maximpestryakov.hands.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.maximpestryakov.hands.data.CellRepository
import com.github.maximpestryakov.hands.ui.MainViewModel
import com.github.maximpestryakov.hands.usecase.GenerateCell
import com.github.maximpestryakov.hands.usecase.GetCellsWithDiff

interface AppModule {

    val viewModelProviderFactory: ViewModelProvider.Factory
}

fun AppModule(dbModule: DbModule): AppModule {
    return object : AppModule {

        val cellRepository: CellRepository by lazy {
            CellRepository(
                dbModule.cellEntityDao
            )
        }

        val generateCell: GenerateCell
            get() = GenerateCell(cellRepository, dbModule.dbLock)

        val getCellsWithDiff: GetCellsWithDiff
            get() = GetCellsWithDiff(cellRepository)

        val mainViewModel: MainViewModel
            get() = MainViewModel(generateCell, getCellsWithDiff)

        override val viewModelProviderFactory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    MainViewModel::class.java -> mainViewModel
                    else -> throw IllegalArgumentException()
                } as T
            }
        }
    }
}
