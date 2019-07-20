package com.pppp.travelchecklist.list.view.recycler.card.recycler

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.recycler.card.recycler.item.CardItemView

class CardAdapter(private val callback: CardItemView.Callback) : RecyclerView.Adapter<CardHolder>() {

    var items: MutableList<CheckListItem> = mutableListOf()
        set(value) {
            //DiffUtil.calculateDiff(CardRecyclerDiffCallback(items, value)).dispatchUpdatesTo(this)
            items.clear()
            items.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(items.get(position), position, callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardHolder(CardItemView(parent.context))

    override fun onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) {
//        if (payloads.isEmpty()) {
//            holder.bind(items.get(position), position, callback)
//            return
//        }
        //
        //TODO()
        //categories[itemPosition].categories = getCategories(payloads)
        holder.bind(items.get(position), position, callback)
    }
}