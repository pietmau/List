package com.pppp.travelchecklist.list.view.recycler.card.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem

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
        ItemTouchHelper(customItemTouchHelperCallback()).attachToRecyclerView(this)
        addItemDecoration(CustomDividerItemDecoration(context))
        setNestedScrollingEnabled(false)
    }

    private fun customItemTouchHelperCallback(): CustomItemTouchHelperCallback {
        return CustomItemTouchHelperCallback { viewHolder ->
            val position = viewHolder.adapterPosition
            val checkListItem = cardAdapter.items[position]
            callback.onDeleteRequested(checkListItem.id, checkListItem)
        }
    }

    class CustomItemTouchHelperCallback(val callback: ((ViewHolder) -> Unit)? = null) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder) = throw Exception("Drag and drop not supportend")

        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            callback?.invoke(viewHolder)
        }
    }
}