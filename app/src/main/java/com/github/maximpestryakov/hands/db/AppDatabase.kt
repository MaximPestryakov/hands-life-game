package com.github.maximpestryakov.hands.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.maximpestryakov.hands.BuildConfig

@Database(
    entities = [CellEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(CellTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val cellEntityDao: CellEntityDao
}
