package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
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
        val indexedCategory = findCategoryById(travelCheckList.categories, categoryId) ?: return
        val categories = travelCheckList.categories.toMutableList()
        val category = indexedCategory.value
        val copy = removeItem(category, itemId)
        if (copy.items.isNullOrEmpty()) {
            categories.removeAt(indexedCategory.index)
        } else {
            categories.set(indexedCategory.index, copy)
        }
        saveChanges(categories, listId)
    }

    private fun removeItem(category: CategoryImpl, itemId: Long): CategoryImpl {
        return category.copy(items = category.items.filter { it.id != itemId })
    }

    private fun saveChanges(categories: MutableList<CategoryImpl>, listId: String) {
        val travelCheckList = travelCheckList.copy(categories = categories.toList())
        repository.updateList(listId, travelCheckList)
    }

    private fun findCategoryById(categories: List<CategoryImpl>, categoryId: Long) = categories.withIndex().find { it.value.id == categoryId }
}