package com.pppp.travelchecklist.model.dao

import android.database.Cursor
import com.pppp.entities.pokos.Category
import com.pppp.entities.pokos.CheckList
import com.pppp.entities.pokos.CheckListItem


interface Deserializer {
    fun getEmptyCheckLists(listsCursor: Cursor?): List<CheckList>
    fun getEmptyCards(cardsCursor: Cursor?): List<Category>
    fun getItems(itemsCursor: Cursor?): Collection<CheckListItem>
    fun getEmptyCheckList(listsCursor: Cursor?): CheckList?
}