package com.github.maximpestryakov.hands.db

import androidx.room.TypeConverter
import com.github.maximpestryakov.hands.data.CellType

object CellTypeConverter {

    @TypeConverter
    @JvmStatic
    fun cellTypeToString(cellType: CellType): String {
        return cellType.name
    }

    @TypeConverter
    @JvmStatic
    fun cellTypeFromString(string: String): CellType {
        return CellType.values().find { it.name == string }!!
    }
}
