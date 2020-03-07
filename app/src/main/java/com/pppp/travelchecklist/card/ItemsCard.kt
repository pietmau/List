package com.pppp.travelchecklist.card

import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.item.CardItemView

interface ItemsCard {
    var callback: CardItemView.Callback

    var items: List<CheckListItem>
}