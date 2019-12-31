package com.pppp.travelchecklist.list.view.card.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.card.item.CardItemView

class CardAdapter(private val callback: CardItemView.Callback) : RecyclerView.Adapter<CardHolder>() {

    var items: MutableList<CheckListItem> = mutableListOf()
        set(value) {
            if (onlyReordering(field, value)) {
                return
            }
            DiffUtil.calculateDiff(CardRecyclerDiffCallback(field, value)).dispatchUpdatesTo(this)
            field.clear()
            field.addAll(value)
        }

    private fun onlyReordering(old: List<CheckListItem>, new: List<CheckListItem>): Boolean {
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
}