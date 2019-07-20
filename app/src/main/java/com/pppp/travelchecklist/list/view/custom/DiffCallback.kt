package com.pppp.travelchecklist.list.view.custom

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl

class DiffCallback(
    private val old: List<Category>,
    private val aNew: List<Category>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old.get(oldItemPosition).id == aNew.get(newItemPosition).id

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = aNew.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old.get(oldItemPosition).equals(aNew.get(newItemPosition))

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newDatum = aNew.get(newItemPosition)
        val oldDatum = old.get(oldItemPosition)
        val bundle = Bundle()
        if (newDatum.title != oldDatum.title) {
            bundle.putString(TITLE_KEY, newDatum.title)
        }
        if (!newDatum.items.equals(oldDatum.items)) {
            bundle.putParcelableArrayList(ITEMS_KEY, getArray(newDatum.items))
        }
        return bundle
    }

    private fun getArray(items: List<CheckListItem>): ArrayList<CheckListItemImpl> {
        var arr = ArrayList<CheckListItemImpl>()
        for (item in items) {
            arr.add(item as CheckListItemImpl)
        }
        return arr
    }

    companion object {
        val TITLE_KEY = "title"
        val ITEMS_KEY = "categories"
    }
}