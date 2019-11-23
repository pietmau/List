package com.pppp.travelchecklist.list.view.card.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.card.item.CardItemView

class CardHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val cardItemView
        get() = (view as CardItemView)

    fun bind(
        checkListItem: CheckListItem,
        callback: CardItemView.Callback
    ) {
        cardItemView.callback = callback
        cardItemView.itemId = checkListItem.id
        cardItemView.data = checkListItem
    }
}