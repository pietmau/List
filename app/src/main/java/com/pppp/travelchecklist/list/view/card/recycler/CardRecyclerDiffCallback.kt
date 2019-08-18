package com.pppp.travelchecklist.list.view.card.recycler

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.pietrantuono.entities.CheckListItem

class CardRecyclerDiffCallback(
    private val old: MutableList<CheckListItem>,
    private val new: MutableList<CheckListItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val areItemsTheSame = old.get(oldItemPosition).id == new.get(newItemPosition).id
        Log.d("foo", "are the same " + areItemsTheSame + "( old " + oldItemPosition + ",  new " + newItemPosition)
        return areItemsTheSame
    }

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val areContentsTheSame = old.get(oldItemPosition).equals(new.get(newItemPosition))
        Log.d("foo", "are contents the same " + areContentsTheSame + "( old " + oldItemPosition + ",  new " + newItemPosition)
        return areContentsTheSame
    }

}