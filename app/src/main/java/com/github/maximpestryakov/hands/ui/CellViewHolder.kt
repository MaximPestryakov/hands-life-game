package com.github.maximpestryakov.hands.ui

import androidx.recyclerview.widget.RecyclerView
import com.github.maximpestryakov.hands.R
import com.github.maximpestryakov.hands.data.CellType
import com.github.maximpestryakov.hands.databinding.ItemCellBinding

class CellViewHolder(
    private val itemBinding: ItemCellBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindCellType(cellType: CellType) {
        itemBinding.bindCellType(cellType)
    }

    private fun ItemCellBinding.bindCellType(cellType: CellType) {
        when (cellType) {
            CellType.Dead -> {
                backgroundColor.setBackgroundResource(R.drawable.gradient_dead)
                foregroundIcon.setBackgroundResource(R.drawable.ic_dead)
                title.setText(R.string.dead_title)
                description.setText(R.string.dead_description)
            }
            CellType.Alive -> {
                backgroundColor.setBackgroundResource(R.drawable.gradient_alive)
                foregroundIcon.setBackgroundResource(R.drawable.ic_alive)
                title.setText(R.string.alive_title)
                description.setText(R.string.alive_description)
            }
            CellType.Life -> {
                backgroundColor.setBackgroundResource(R.drawable.gradient_life)
                foregroundIcon.setBackgroundResource(R.drawable.ic_life)
                title.setText(R.string.life_title)
                description.setText(R.string.life_description)
            }
        }
    }
}
