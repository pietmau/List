package com.pppp.travelchecklist.list

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.model.CardItemData


class CheckListRecycler(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    var callback: CheckListCard.Callback? = null

    init {
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(context.resources.getDrawable(R.drawable.divider))
        addItemDecoration(dividerItemDecoration)
    }

    fun setItems(new: List<CardItemData>) {
        if (adapter == null) {
            adapter = CheckListAdapter(new, callback)
            return
        }
        val old = (adapter as CheckListAdapter).items
        DiffUtil.calculateDiff(DiffCallback(old, new)).dispatchUpdatesTo(adapter)
    }
}

