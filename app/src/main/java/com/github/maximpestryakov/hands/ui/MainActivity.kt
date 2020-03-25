package com.github.maximpestryakov.hands.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.github.maximpestryakov.hands.R
import com.github.maximpestryakov.hands.appComponent
import com.github.maximpestryakov.hands.databinding.ActivityMainBinding
import com.github.maximpestryakov.hands.utils.MarginItemDecoration
import com.github.maximpestryakov.hands.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val mainViewModel: MainViewModel by viewModels { appComponent.viewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cellAdapter = CellAdapter()
        binding.recyclerView.adapter = cellAdapter
        binding.recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelOffset(R.dimen.margin_between_cells))
        )

        cellAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.recyclerView.smoothScrollToPosition(cellAdapter.itemCount)
            }
        })
        mainViewModel.cellList.observe(this, Observer { listWithDiffResult ->
            cellAdapter.cellList = listWithDiffResult.list
            listWithDiffResult.diffResult?.dispatchUpdatesTo(cellAdapter)
        })

        binding.generateCell.setOnClickListener { mainViewModel.onGenerateButtonClicked() }
    }
}
