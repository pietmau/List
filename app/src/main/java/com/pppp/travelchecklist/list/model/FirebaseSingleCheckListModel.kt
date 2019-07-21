package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
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

    override fun deleteItem(listId: String, categoryId: Long, itemId: Long) {
        val indexedCategory = findCategoryById(travelCheckList.categories, categoryId)
        indexedCategory ?: return
        val categories = travelCheckList.categories.toMutableList()
        val copy = copyCategory(indexedCategory.value, itemId)
        categories.set(indexedCategory.index, copy)
        val travelCheckList = travelCheckList.copy(categories = categories.toList())
        repository.updateList(listId, travelCheckList)
    }

    private fun copyCategory(category: CategoryImpl, itemId: Long) = category.copy(items = removeItemById(category, itemId))

    private fun removeItemById(indexedCategory: CategoryImpl, itemId: Long): List<CheckListItemImpl> = indexedCategory.items.filter { it.id != itemId }

    private fun findCategoryById(categories: List<CategoryImpl>, categoryId: Long) = categories.withIndex().find { it.value.id == categoryId }
}