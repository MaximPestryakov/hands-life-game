package com.github.maximpestryakov.hands.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.maximpestryakov.hands.data.CellType
import com.github.maximpestryakov.hands.data.ListWithDiffResult
import com.github.maximpestryakov.hands.usecase.GenerateCell
import com.github.maximpestryakov.hands.usecase.GetCellsWithDiff
import com.github.maximpestryakov.hands.utils.fastLazy
import kotlinx.coroutines.launch

class MainViewModel(
    private val generateCell: GenerateCell,
    private val getCellsWithDiff: GetCellsWithDiff
) : ViewModel() {

    val cellList: LiveData<ListWithDiffResult<CellType>> by fastLazy {
        getCellsWithDiff().asLiveData(viewModelScope.coroutineContext)
    }

    fun onGenerateButtonClicked() {
        viewModelScope.launch {
            generateCell()
        }
    }
}
