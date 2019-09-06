package com.pppp.travelchecklist.list.view.list

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import com.pietrantuono.entities.Category
import com.pppp.travelchecklist.list.view.card.ChackListCardCallback

class CheckListRecycler @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs) {
    lateinit var chackListCardCallback: ChackListCardCallback
    private val checkListAdapter
        get() = adapter as CheckListAdapter

    init {
        layoutManager = LinearLayoutManager(context)
    }

    fun setItems(categories: List<Category>) {
        if (adapter == null) {
            adapter = CheckListAdapter(chackListCardCallback)
        }
        checkListAdapter.items = categories.toMutableList()
    }
}

