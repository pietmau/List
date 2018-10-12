package com.pppp.travelchecklist.list

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.entities.pokos.Category
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.CheckListCard


class CheckListRecycler(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    var callback: CheckListCard.Callback? = null

    init {
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(context.resources.getDrawable(R.drawable.divider))
        addItemDecoration(dividerItemDecoration)
    }

    fun setItems(aNew: List<Category>) {
        if (adapter == null) {
            adapter = CheckListAdapter(aNew, callback)
            return
        }
        val old = (adapter as CheckListAdapter).items
        DiffUtil.calculateDiff(DiffCallback(old, aNew)).dispatchUpdatesTo(adapter)
    }
}

