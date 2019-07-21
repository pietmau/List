package com.pppp.travelchecklist.list.view.recycler.card.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.recycler.card.recycler.item.CardItemView

class CardRecycler @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    lateinit var callback: CardItemView.Callback

    private val cardAdapter: CardAdapter
        get() = adapter as CardAdapter

    var items: List<CheckListItem> = mutableListOf()
        set(value) {
            if (adapter == null) {
                adapter = CardAdapter(callback)
            }
            cardAdapter.items = value.toMutableList()
        }

    init {
        layoutManager = CustomLayoutManager(context)
    }
}