package com.pppp.travelchecklist.list.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.card.ChackListCardCallback
import com.pppp.travelchecklist.list.view.list.ChecklistRecyclerDiffCallback.Companion.CATEGORY

class CheckListAdapter(private val chackListCardCallback: ChackListCardCallback) : RecyclerView.Adapter<CheckListHolder>() {

    override fun getItemCount() = items.count()

    var items: MutableList<Category> = mutableListOf()
        set(value) {
            DiffUtil.calculateDiff(ChecklistRecyclerDiffCallback(items, value)).dispatchUpdatesTo(this)
            field.clear()
            field.addAll(value)
        }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int) {
        holder.bind(items.get(position), chackListCardCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_no_card, null)
        return CheckListHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bind(items.get(position), chackListCardCallback)
            return
        }
        (payloads[0] as Bundle).getParcelable<CategoryImpl>(CATEGORY)?.let { holder.setItems(it.items) }
    }
}

