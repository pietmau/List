package com.pppp.travelchecklist.list

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.CheckListCard

class CheckListRecycler @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs) {
    var callback: CheckListCard.Callback? = null

    init {
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, androidx.recyclerview.widget.RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(context.resources.getDrawable(R.drawable.divider))
        addItemDecoration(dividerItemDecoration)
    }

    fun setItems(aNew: List<CategoryImpl>) {
        if (adapter == null) {
            adapter = CheckListAdapter(aNew, callback)
            return
        }
        val old = (adapter as CheckListAdapter).items
        //DiffUtil.calculateDiff(DiffCallback(old, aNew)).dispatchUpdatesTo(adapter)
    }
}

