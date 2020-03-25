package com.github.maximpestryakov.hands.utils

import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalculateDiff<in T : Any>(
    private val itemCallback: DiffUtil.ItemCallback<in T> = EqualityItemCallback,
    private val detectMoves: Boolean = true
) {

    suspend operator fun invoke(
        oldList: List<T>,
        newList: List<T>
    ): DiffUtil.DiffResult = withContext(Dispatchers.Default) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return itemCallback.areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return itemCallback.areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                return itemCallback.getChangePayload(oldList[oldItemPosition], newList[newItemPosition])
            }
        }, detectMoves)
    }

    private companion object EqualityItemCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}
