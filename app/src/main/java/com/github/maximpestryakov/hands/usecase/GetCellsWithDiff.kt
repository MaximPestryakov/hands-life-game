package com.github.maximpestryakov.hands.usecase

import com.github.maximpestryakov.hands.data.CellRepository
import com.github.maximpestryakov.hands.data.CellType
import com.github.maximpestryakov.hands.data.ListWithDiffResult
import com.github.maximpestryakov.hands.db.CellEntity
import com.github.maximpestryakov.hands.utils.CalculateDiff
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan

class GetCellsWithDiff(
    private val cellRepository: CellRepository
) {

    private val calculateDiff = CalculateDiff<CellEntity>(detectMoves = false)

    operator fun invoke(): Flow<ListWithDiffResult<CellType>> {
        @Suppress("EXPERIMENTAL_API_USAGE")
        return cellRepository.getCellEntityListFlow()
            .scan(ListWithDiffResult<CellEntity>()) { previousListWithDiff, newList ->
                ListWithDiffResult(
                    newList,
                    calculateDiff(previousListWithDiff.list, newList)
                )
            }
            .map { listWithDiffResult ->
                ListWithDiffResult(
                    listWithDiffResult.list.map { it.type },
                    listWithDiffResult.diffResult
                )
            }
    }
}
