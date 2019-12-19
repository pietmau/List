package com.pppp.travelchecklist.list.view.list

import android.view.View
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.travelchecklist.list.view.card.ChackListCardCallback
import com.pppp.travelchecklist.list.view.card.CheckListCard

class CheckListHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bind(categoryItemData: Category, chackListCardCallback: ChackListCardCallback) {
        (itemView as CheckListCard).bind(categoryItemData, chackListCardCallback)
    }

    fun setItems(items: List<RoomCheckListItem>) {
        (itemView as CheckListCard).setItems(items)
    }

}