package com.pppp.travelchecklist.list.view.custom

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import com.pietrantuono.entities.Category
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.CheckListCard

class CheckListRecycler @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs) {
    lateinit var callback: CheckListCard.Callback

    init {
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(context.resources.getDrawable(R.drawable.divider))
        addItemDecoration(dividerItemDecoration)
    }

    fun setItems(aNew: List<Category>) {
        if (adapter == null) {
            adapter = CheckListAdapter(aNew, callback)
        }
        val old = (adapter as CheckListAdapter).items
        DiffUtil.calculateDiff(DiffCallback(old, aNew)).dispatchUpdatesTo(adapter!!)
    }
}

