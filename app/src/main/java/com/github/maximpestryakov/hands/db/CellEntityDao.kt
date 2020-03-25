package com.github.maximpestryakov.hands.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CellEntityDao {

    @Query("SELECT * FROM CellEntity")
    fun getAllEntitiesFlow(): Flow<List<CellEntity>>

    @Query("SELECT * FROM CellEntity")
    suspend fun getAllEntities(): List<CellEntity>

    @Insert
    suspend fun insertEntity(cellEntities: List<CellEntity>)

    @Query("DELETE FROM CellEntity WHERE id = :id")
    suspend fun removeEntityById(id: Int)
}
