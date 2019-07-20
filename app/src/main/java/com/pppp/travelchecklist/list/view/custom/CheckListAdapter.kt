package com.pppp.travelchecklist.list.view.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.CheckListCard


class CheckListAdapter(
    var items: List<Category>,
    private val callback: CheckListCard.Callback

) : androidx.recyclerview.widget.RecyclerView.Adapter<CheckListHolder>() {

    override fun onBindViewHolder(holder: CheckListHolder, position: Int) {
        holder.bind(items.get(position), position, callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, null)
        return CheckListHolder(view)
    }

    override fun getItemCount()= items.count()

    override fun onBindViewHolder(holder: CheckListHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bind(items.get(position), position, callback)
            return
        }
        //
        TODO()
        //categories[itemPosition].categories = getCategories(payloads)
        holder.bind(items.get(position), position, callback)
    }

    private fun getItems(payloads: MutableList<Any>): List<CheckListItemImpl> {
        (payloads[0] as? Bundle)?.getParcelableArrayList<CheckListItemImpl>(DiffCallback.ITEMS_KEY)?.let {
            return it
        }
        return emptyList()
    }
}

