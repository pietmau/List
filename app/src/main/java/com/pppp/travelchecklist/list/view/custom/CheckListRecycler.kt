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
    private val checkListAdapter
        get() = adapter as CheckListAdapter

    init {
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(context.resources.getDrawable(R.drawable.divider))
        addItemDecoration(dividerItemDecoration)
    }

    fun setItems(categories: List<Category>) {
        if (adapter == null) {
            adapter = CheckListAdapter(callback)
        }
        checkListAdapter.items = categories.toMutableList()
    }
}

