package com.pppp.travelchecklist.card.recycler

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.pietrantuono.entities.CheckListItem

class CardRecyclerDiffCallback(
    private val old: List<CheckListItem>,
    private val new: List<CheckListItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val areItemsTheSame = old.get(oldItemPosition).id == new.get(newItemPosition).id
        return areItemsTheSame
    }

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val areContentsTheSame = old.get(oldItemPosition).equals(new.get(newItemPosition))
        return areContentsTheSame
    }

}