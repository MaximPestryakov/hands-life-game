package com.github.maximpestryakov.hands.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.maximpestryakov.hands.data.CellType
import com.github.maximpestryakov.hands.databinding.ItemCellBinding

class CellAdapter : RecyclerView.Adapter<CellViewHolder>() {

    var cellList: List<CellType> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CellViewHolder(ItemCellBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount() = cellList.size

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bindCellType(cellList[position])
    }
}
