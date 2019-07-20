package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.SingleCheckListRepository

class FirebaseSingleCheckListModel(private val repository: SingleCheckListRepository) : SingleCheckListModel {
    private lateinit var travelCheckList: TravelCheckListImpl
    private lateinit var listId: String

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?) {
        this.listId = listId
        repository.getUserCheckListAndUpdates(listId, success = {
            this@FirebaseSingleCheckListModel.travelCheckList = it as TravelCheckListImpl
            success?.invoke(it)
        })
    }

    override fun deleteItem(listId: String, categoryPosition: Int, itemPosition: Int) {
        val categories = travelCheckList.categories.toMutableList()
        val category = categories[categoryPosition]
        val items = category.items
            .withIndex()
            .filter { it.index != itemPosition }
            .map { it.value }
        categories[categoryPosition] = category.copy(items = items)
        repository.updateList(listId, travelCheckList.copy(categories = categories))
    }
}