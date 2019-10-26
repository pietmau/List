package com.pppp.travelchecklist.list.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.card.ChackListCardCallback
import com.pppp.travelchecklist.list.view.list.ChecklistRecyclerDiffCallback.Companion.CATEGORY

class CheckListAdapter(private val chackListCardCallback: ChackListCardCallback, private var showChecked: Boolean) : RecyclerView.Adapter<CheckListHolder>(),
    Filterable {
    override fun getFilter() = CheckedFilter()

    private var categories: List<Category> = emptyList()

    private var showingfItems: MutableList<Category> = mutableListOf()

    override fun getItemCount() = showingfItems.count()

    fun setItems(categories: List<Category>, showChecked: Boolean) {
        this.categories = categories
        this.showChecked  =showChecked
        filter.filter(null)
    }

    fun setItemsInternal(categories: List<Category>) {
        DiffUtil.calculateDiff(ChecklistRecyclerDiffCallback(showingfItems, categories)).dispatchUpdatesTo(this)
        showingfItems.clear()
        showingfItems.addAll(categories)
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int) {
        holder.bind(showingfItems.get(position), chackListCardCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_no_card, null)
        return CheckListHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bind(showingfItems.get(position), chackListCardCallback)
            return
        }
        (payloads[0] as Bundle).getParcelable<CategoryImpl>(CATEGORY)?.let { holder.setItems(it.items) }
    }

    inner class CheckedFilter : Filter() {
        @Suppress("UNCHECKED_CAST")
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val categories = this@CheckListAdapter.categories.map { category ->
                val checklistItems = category.items.filter { !it.checked || showChecked }
                (category as CategoryImpl).copy(items = checklistItems as List<CheckListItemImpl>)
            }
            return FilterResults().apply {
                values = categories
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val results = results?.values as? List<Category> ?: emptyList()
            setItemsInternal(results)
        }
    }

}

