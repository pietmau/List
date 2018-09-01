package com.pppp.travelchecklist.model.dao

import android.database.Cursor
import com.pppp.travelchecklist.model.Category
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData


interface Deserializer {
    fun getEmptyCheckLists(listsCursor: Cursor?): List<CheckList>
    fun getEmptyCards(cardsCursor: Cursor?): List<Category>
    fun getItems(itemsCursor: Cursor?): Collection<CheckListItemData>
    fun getEmptyCheckList(listsCursor: Cursor?): CheckList?
}