package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import java.util.Collections

class FirebaseSingleCheckListModel(private val repository: SingleCheckListRepository) : SingleCheckListModel {
    private lateinit var travelCheckList: TravelCheckListImpl
    private lateinit var listId: String

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?) {
        this.listId = listId
        repository.getUserCheckList(listId, success = {
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

    override fun moveItems(cardId: Long, fromPosition: Int, toPosition: Int) {
        val indexedCategory = findCategoryById(travelCheckList.categories, cardId) ?: return
        val categories = travelCheckList.categories.toMutableList()
        val category = indexedCategory.value
        val copy = swapItems(category, fromPosition, toPosition)
        categories.set(indexedCategory.index, copy)
        saveChanges(categories, listId)
    }

    private fun swapItems(category: CategoryImpl, fromPosition: Int, toPosition: Int): CategoryImpl {
        var items = category.items.toMutableList()
        Collections.swap(items, fromPosition, toPosition)
        return category.copy(items = items.toList())
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