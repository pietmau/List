package com.pppp.travelchecklist.list

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.travelchecklist.card.CheckListCard

class CheckListHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bind(categoryItemData: CategoryImpl, position: Int, callback: CheckListCard.Callback?) {
        (itemView as CheckListCard).bind(categoryItemData.items, position, callback)
    }

}