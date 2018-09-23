package com.pppp.travelchecklist.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.entities.Category

class CheckListHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(categoryItemData: Category, position: Int, callback: CheckListCard.Callback?) {
        (itemView as CheckListCard).bind(categoryItemData.items, position, callback)
    }

}