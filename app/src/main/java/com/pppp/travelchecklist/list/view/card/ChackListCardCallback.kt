package com.pppp.travelchecklist.list.view.card

import com.pietrantuono.entities.CheckListItem

interface ChackListCardCallback {
    fun onItemDeleteRequested(cardId: String, itemId: String, data: CheckListItem)

    fun onItemChecked(cardId: String, itemId: String, checked: Boolean)

    fun onItemMoved(cardId: String, fromPosition: Int, toPosition: Int)

    fun onCardOptionsClicked(cardId: String)

    fun onSettingsClicked(cardId: String, itemId: String)
}