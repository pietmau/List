package com.pppp.travelchecklist.list.view.recycler.card.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.view.recycler.card.recycler.item.CardItemView

class CardHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val cardItemView
        get() = (view as CardItemView)

    fun bind(checkListItem: CheckListItem, position: Int, callback: CardItemView.Callback) {
        cardItemView.callback = callback
        cardItemView.itemPosition = position
        cardItemView.data = checkListItem
    }
}