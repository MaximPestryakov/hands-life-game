package com.github.maximpestryakov.hands.data

import androidx.recyclerview.widget.DiffUtil

data class ListWithDiffResult<out T>(
    val list: List<T> = emptyList(),
    val diffResult: DiffUtil.DiffResult? = null
)
