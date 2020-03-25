package com.github.maximpestryakov.hands.data

import com.github.maximpestryakov.hands.db.CellEntity
import com.github.maximpestryakov.hands.db.CellEntityDao
import kotlinx.coroutines.flow.Flow

class CellRepository(
    private val cellEntityDao: CellEntityDao
) {

    suspend fun addCells(vararg cellTypes: CellType) {
        cellEntityDao.insertEntity(cellTypes.map { CellEntity(type = it) })
    }

    suspend fun removeCellById(id: Int) {
        cellEntityDao.removeEntityById(id)
    }

    suspend fun getCellEntityList(): List<CellEntity> {
        return cellEntityDao.getAllEntities()
    }

    fun getCellEntityListFlow(): Flow<List<CellEntity>> {
        return cellEntityDao.getAllEntitiesFlow()
    }
}
