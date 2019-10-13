package com.pppp.travelchecklist.list.view.card.recycler

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.card.item.CardItemView

class CardAdapter(private val callback: CardItemView.Callback, private var showChecked: Boolean) : RecyclerView.Adapter<CardHolder>(), Filterable {
    private var showedItems: MutableList<CheckListItem> = mutableListOf()

    override fun getFilter() = CardFilter()

    var items: MutableList<CheckListItem> = mutableListOf()
        set(value) {
            field = value
            filter.filter(null)
        }
        get() = field

    private fun setShowingItems(items: List<CheckListItem>) {
        if (onlyReordering(items, showedItems)) {
            return
        }
        DiffUtil.calculateDiff(CardRecyclerDiffCallback(showedItems, items)).dispatchUpdatesTo(this)
        showedItems.clear()
        showedItems.addAll(items)
    }

    private fun onlyReordering(old: List<CheckListItem>, new: List<CheckListItem>): Boolean {
        if (old.size != new.size) {
            return false
        }
        return new.sortedBy { it.id } == old.sortedBy { it.id }
    }

    override fun getItemCount() = showedItems.count()

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(showedItems.get(position), callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardHolder(CardItemView(parent.context))

    override fun onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) {
        holder.bind(showedItems.get(position), callback)
    }

    fun setShowChecked(value: Boolean) {
        showChecked = value
    }

    inner class CardFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = if (showChecked) items.toList() else items.filter { it.checked == false }
            return FilterResults().apply { values = results }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val items = (results?.values as? List<CheckListItem>) ?: emptyList()
            setShowingItems(items)
        }
    }
}