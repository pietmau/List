package com.pppp.travelchecklist.list.view.card.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.card.item.CardItemView

class CardAdapter(private val callback: CardItemView.Callback) : RecyclerView.Adapter<CardHolder>() {

    var items: MutableList<CheckListItem> = mutableListOf()
        set(value) {
            DiffUtil.calculateDiff(CardRecyclerDiffCallback(items, value)).dispatchUpdatesTo(this)
            field.clear()
            field.addAll(value)
        }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(items.get(position), callback)
        holder.setIsRecyclable(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardHolder(CardItemView(parent.context))

    override fun onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) {
        holder.bind(items.get(position), callback)
        holder.setIsRecyclable(false)
    }
}