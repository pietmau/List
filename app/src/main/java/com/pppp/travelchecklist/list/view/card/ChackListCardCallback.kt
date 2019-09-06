package com.pppp.travelchecklist.list.view.card

import com.pietrantuono.entities.CheckListItem

interface ChackListCardCallback {
    fun onItemDeleteRequested(cardId: Long, itemId: Long, data: CheckListItem)

    fun onItemChecked(cardId: Long, itemId: Long, checked: Boolean)

    fun onItemMoved(cardId: Long, fromPosition: Int, toPosition: Int)

    fun onCardOptionsClicked(cardId: Long)
}