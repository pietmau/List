package com.pppp.travelchecklist.list.view.recycler.card.recycler

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.recycler.card.recycler.item.CardItemView

class CardAdapter(private val callback: CardItemView.Callback) : RecyclerView.Adapter<CardHolder>() {

    var items: MutableList<CheckListItem> = mutableListOf()
        set(value) {
            DiffUtil.calculateDiff(CardRecyclerDiffCallback(items, value)).dispatchUpdatesTo(this)
            Log.d("foo", " old " + items.toString())
            field.clear()
            field.addAll(value)
            Log.d("foo", " new " + items.toString())
        }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        Log.d("foo", " onBindViewHolder(holder: CardHolder, position: Int) POSITION = " + position)
        holder.bind(items.get(position), callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardHolder(CardItemView(parent.context))

    override fun onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) {
        Log.d("foo", " onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) POSITION = " + position)
        holder.bind(items.get(position), callback)
    }
}