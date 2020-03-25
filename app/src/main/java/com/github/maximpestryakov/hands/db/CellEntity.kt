package com.github.maximpestryakov.hands.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.maximpestryakov.hands.data.CellType

@Entity
data class CellEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: CellType
)
