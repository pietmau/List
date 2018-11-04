package com.pppp.travelchecklist.model.dao

import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl


interface ListDao {
    fun getCheckLists(): List<CheckListImpl>
    fun getCheckListById(checklistId: Long): CheckListImpl
    fun editItem(item: CheckListItemImpl): Int
    fun deleteItem(it: CheckListItemImpl): Int
}