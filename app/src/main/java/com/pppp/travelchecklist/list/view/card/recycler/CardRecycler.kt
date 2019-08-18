package com.pppp.travelchecklist.list.view.card.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.card.item.CardItemView

class CardRecycler @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    lateinit var callback: CardItemView.Callback

    private val cardAdapter: CardAdapter
        get() = adapter as CardAdapter

    private var itemTouchHelper: ItemTouchHelper? = null

    var items: List<CheckListItem>
        set(value) {
            if (adapter == null) {
                adapter = CardAdapter(callback)
            }
            cardAdapter.items = value.toMutableList()
            if (itemTouchHelper == null) {
                itemTouchHelper = ItemTouchHelper(CustomItemTouchHelperCallback(cardAdapter, callback))
                itemTouchHelper?.attachToRecyclerView(this)
            }
        }
        get() = cardAdapter.items

    init {
        layoutManager = CustomLayoutManager(context)
        addItemDecoration(CustomDividerItemDecoration(context))
        setNestedScrollingEnabled(false)
    }

    class CustomItemTouchHelperCallback(private val adapter: CardAdapter, private val callback: CardItemView.Callback) :
        ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            val cardRecycler = recyclerView as CardRecycler
            val items = cardRecycler.items.toMutableList()
            val prev = items.removeAt(fromPosition)
            items.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, prev)
            cardRecycler.items = items
            callback.onItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val item = adapter.items[position]
            callback.onDeleteRequested(item.id, item)
        }

    }
}