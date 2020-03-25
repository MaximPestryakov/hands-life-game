package com.github.maximpestryakov.hands.usecase

import com.github.maximpestryakov.hands.data.CellRepository
import com.github.maximpestryakov.hands.data.CellType
import com.github.maximpestryakov.hands.utils.DbLock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GenerateCell(
    private val cellRepository: CellRepository,
    private val dbLock: DbLock,
    private val random: Random = Random.Default
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        dbLock.withTransaction {
            if (random.nextBoolean()) {
                generateAlive()
            } else {
                generateDead()
            }
        }
    }

    private suspend fun generateAlive() {
        cellRepository.addCells(CellType.Alive)
        val cellList = cellRepository.getCellEntityList()
        if (cellList.size >= COUNT && cellList.takeLast(COUNT).all { it.type == CellType.Alive }) {
            cellRepository.addCells(CellType.Life)
        }
    }

    private suspend fun generateDead() {
        cellRepository.addCells(CellType.Dead)
        val cellList = cellRepository.getCellEntityList()
        if (cellList.size >= COUNT + 1) {
            val neighborCell = cellList[cellList.lastIndex - COUNT]
            if (neighborCell.type == CellType.Life && cellList.takeLast(COUNT).all { it.type == CellType.Dead }) {
                cellRepository.removeCellById(neighborCell.id)
            }
        }
    }

    private companion object {
        const val COUNT = 3
    }
}
