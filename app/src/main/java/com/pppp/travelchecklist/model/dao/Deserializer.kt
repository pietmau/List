package com.pppp.travelchecklist.model.dao

import android.database.Cursor
import com.pppp.travelchecklist.model.Card
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData


interface Deserializer {
    fun getEmptyCheckLists(listsCursor: Cursor?): List<CheckList>
    fun getEmptyCards(cardsCursor: Cursor?): List<Card>
    fun getItems(itemsCursor: Cursor?): Collection<CheckListItemData>
    fun getEmptyCheckList(listsCursor: Cursor?): CheckList?
}