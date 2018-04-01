package com.pppp.travelchecklist.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.model.Card

class CheckListHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(cardItemData: Card, position: Int, callback: CheckListCard.Callback?) {
        (itemView as CheckListCard).bind(cardItemData.items, position, callback)
    }

}