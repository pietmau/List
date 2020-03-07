package com.pppp.travelchecklist.list.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.ChackListCardCallback
import com.pppp.travelchecklist.list.view.list.ChecklistRecyclerDiffCallback.Companion.CATEGORY

class CheckListAdapter(var chackListCardCallback: ChackListCardCallback? = null, private var showChecked: Boolean = true) :
    RecyclerView.Adapter<CheckListHolder>() {

    private var items: MutableList<Category> = mutableListOf()

    override fun getItemCount() = items.count()

    fun setItems(categories: List<Category>, showChecked: Boolean) {
        this.showChecked = showChecked
        val filtered = filterList(categories, showChecked)
        DiffUtil.calculateDiff(ChecklistRecyclerDiffCallback(items, filtered)).dispatchUpdatesTo(this)
        items.clear()
        items.addAll(filtered)
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int) {
        holder.bind(items.get(position), chackListCardCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListHolder {
        return CheckListHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_no_card, null))
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bind(items.get(position), chackListCardCallback)
            return
        }
        (payloads[0] as Bundle).getParcelable<CategoryImpl>(CATEGORY)?.let { holder.setItems(it.items) }
    }

    private fun filterList(categories: List<Category>, showChecked: Boolean) =
        categories.map { category ->
            val checklistItems = category.items.filter { !it.checked || showChecked }
            (category as CategoryImpl).copy(items = checklistItems as List<CheckListItemImpl>)
        }

}

