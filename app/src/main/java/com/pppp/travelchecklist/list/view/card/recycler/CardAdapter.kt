package com.pppp.travelchecklist.list.view.card.recycler

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.card.item.CardItemView

class CardAdapter(private val callback: CardItemView.Callback, private var showChecked: Boolean) : RecyclerView.Adapter<CardHolder>(), Filterable {

    private var itemsInternal: MutableList<CheckListItem> = mutableListOf()

    override fun getFilter(): Filter {

    }

    fun setItem(items: List<CheckListItem>) {
        if (onlyReordering(items, itemsInternal)) {
            return
        }
        DiffUtil.calculateDiff(CardRecyclerDiffCallback(itemsInternal, items)).dispatchUpdatesTo(this)
        itemsInternal.clear()
        itemsInternal.addAll(items)
    }

    private fun onlyReordering(old: List<CheckListItem>, new List<CheckListItem>): Boolean {
        if (old.size != new.size) {
            return false
        }
        return new.sortedBy { it.id } == old.sortedBy { it.id }
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(items.get(position), callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardHolder(CardItemView(parent.context))

    override fun onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) {
        holder.bind(items.get(position), callback)
    }

    fun setShowChecked(value: Boolean) {
        showChecked = value
    }

    inner class CardFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}