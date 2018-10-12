package com.pppp.travelchecklist.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.entities.pokos.Category
import com.pppp.travelchecklist.card.CheckListCard

class CheckListHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(categoryItemData: Category, position: Int, callback: CheckListCard.Callback?) {
        (itemView as CheckListCard).bind(categoryItemData.items, position, callback)
    }

}