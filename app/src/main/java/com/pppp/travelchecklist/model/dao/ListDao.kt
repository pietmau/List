package com.pppp.travelchecklist.model.dao

import com.pppp.travelchecklist.model.CheckList
import com.pppp.entities.CheckListItem


interface ListDao {
    fun getCheckLists(): List<CheckList>
    fun getCheckListById(checklistId: Long): CheckList
    fun editItem(item: CheckListItem): Int
    fun deleteItem(it: CheckListItem): Int
}