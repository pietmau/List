package com.pppp.travelchecklist.list.view.recycler.list

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CategoryImpl

class ChecklistRecyclerDiffCallback(
    private val old: List<Category>,
    private val new: List<Category>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old.get(oldItemPosition).id == new.get(newItemPosition).id

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old.get(oldItemPosition).equals(new.get(newItemPosition))

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) =
        Bundle().apply { putParcelable(CATEGORY, this@ChecklistRecyclerDiffCallback.new.get(newItemPosition) as CategoryImpl) }

    companion object {
        val CATEGORY = "category"
    }
}