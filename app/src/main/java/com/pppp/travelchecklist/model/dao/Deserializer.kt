package com.pppp.travelchecklist.model.dao

import android.database.Cursor
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl


interface Deserializer {
    fun getEmptyCheckLists(listsCursor: Cursor?): List<CheckListImpl>
    fun getEmptyCards(cardsCursor: Cursor?): List<CategoryImpl>
    fun getItems(itemsCursor: Cursor?): Collection<CheckListItemImpl>
    fun getEmptyCheckList(listsCursor: Cursor?): CheckListImpl?
}