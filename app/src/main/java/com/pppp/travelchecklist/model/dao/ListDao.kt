package com.pppp.travelchecklist.model.dao

import com.pppp.entities.pokos.CheckList
import com.pppp.entities.pokos.CheckListItem


interface ListDao {
    fun getCheckLists(): List<CheckList>
    fun getCheckListById(checklistId: Long): CheckList
    fun editItem(item: CheckListItem): Int
    fun deleteItem(it: CheckListItem): Int
}