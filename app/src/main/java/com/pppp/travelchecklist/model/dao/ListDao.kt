package com.pppp.travelchecklist.model.dao

import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData


interface ListDao {
    fun getCheckLists(): List<CheckList>
    fun getCheckListById(checklistId: Long): CheckList
    fun editItem(item: CheckListItemData): Int
    fun deleteItem(it: CheckListItemData): Int
}