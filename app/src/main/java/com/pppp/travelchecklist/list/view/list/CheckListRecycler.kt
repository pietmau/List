package com.pppp.travelchecklist.list.view.list

import android.content.Context
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.card.ChackListCardCallback

class CheckListRecycler @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs) {
    var checkListCardCallback: ChackListCardCallback? = null
        set(value) {
            checkListAdapter.chackListCardCallback = value
        }

    private val checkListAdapter
        get() = adapter as CheckListAdapter

    init {
        layoutManager = LinearLayoutManager(context)
        adapter = CheckListAdapter()
    }

    fun setItems(categories: List<Category>, showChecked: Boolean) {
        checkListAdapter.setItems(categories.toMutableList(), showChecked)
    }
}

