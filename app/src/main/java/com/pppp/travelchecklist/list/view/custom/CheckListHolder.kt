package com.pppp.travelchecklist.list.view.custom

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.travelchecklist.card.CheckListCard

class CheckListHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bind(categoryItemData: Category, position: Int, callback: CheckListCard.Callback) {
        (itemView as CheckListCard).bind(categoryItemData.items, position, callback)
    }

}