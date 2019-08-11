package com.pppp.travelchecklist.list.view.recycler.card.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem

class CardHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val cardItemView
        get() = (view as CardItemView)

    fun bind(checkListItem: CheckListItem, callback: CardItemView.Callback) {
        cardItemView.callback = callback
        cardItemView.itemId = checkListItem.id
        cardItemView.data = checkListItem
    }
}