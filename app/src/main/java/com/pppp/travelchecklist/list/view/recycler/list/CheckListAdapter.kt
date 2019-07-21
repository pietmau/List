package com.pppp.travelchecklist.list.view.recycler.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.recycler.card.CheckListCard
import com.pppp.travelchecklist.list.view.recycler.list.ChecklistRecyclerDiffCallback.Companion.CATEGORY

class CheckListAdapter(
    private val callback: CheckListCard.Callback

) : RecyclerView.Adapter<CheckListHolder>() {

    var items: MutableList<Category> = mutableListOf()
        set(value) {
            DiffUtil.calculateDiff(ChecklistRecyclerDiffCallback(items, value)).dispatchUpdatesTo(this)
            items.clear()
            items.addAll(value)
        }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CheckListHolder, position: Int) {
        holder.bind(items.get(position), position, callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, null)
        return CheckListHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bind(items.get(position), position, callback)
            return
        }
        (payloads[0] as Bundle).getParcelable<CategoryImpl>(CATEGORY)?.let { holder.setItems(it.items) }
    }
}

