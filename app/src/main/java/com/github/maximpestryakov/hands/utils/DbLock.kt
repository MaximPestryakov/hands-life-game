package com.github.maximpestryakov.hands.utils

import androidx.room.RoomDatabase
import androidx.room.withTransaction

class DbLock(
    private val roomDatabase: RoomDatabase
) {

    suspend fun <R> withTransaction(block: suspend () -> R) = roomDatabase.withTransaction(block)
}
