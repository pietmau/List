package com.pppp.travelchecklist.card

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem

interface CheckListCard {
    var checkListCardCallback: ChackListCardCallback?
    fun bind(category: Category, chackListCardCallback: ChackListCardCallback?)
    fun setItems(items: List<CheckListItem>)
}