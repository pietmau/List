package com.pppp.travelchecklist.list

import android.os.Bundle
import android.support.v7.util.DiffUtil
import com.pppp.travelchecklist.model.Category
import com.pppp.travelchecklist.model.CheckListItemData


class DiffCallback(
    private val old: List<Category>,
    private val aNew: List<Category>)
    : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
            = old.get(oldItemPosition).id == aNew.get(newItemPosition).id

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = aNew.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
            = old.get(oldItemPosition).equals(aNew.get(newItemPosition))

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

    private fun getArray(items: List<CheckListItemData>): ArrayList<CheckListItemData> {
        var arr = ArrayList<CheckListItemData>()
        for (item in items) {
            arr.add(item)
        }
        return arr
    }

    companion object {
        val TITLE_KEY = "title"
        val ITEMS_KEY = "items"
    }
}