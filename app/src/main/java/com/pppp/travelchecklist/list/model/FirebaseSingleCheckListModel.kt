package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import java.util.Collections

class FirebaseSingleCheckListModel(private val repository: SingleCheckListRepository) : SingleCheckListModel {
    private lateinit var travelCheckList: TravelCheckListImpl
    private lateinit var listId: String

    override fun checkItem(cardId: Long, itemId: Long, checked: Boolean) {
        val indexedCategory = findCategoryById(travelCheckList.categories, cardId) ?: return
        val categories = travelCheckList.categories.toMutableList()
        val copy = checkItemInternal(indexedCategory, itemId, checked)
        categories.set(indexedCategory.index, copy)
        saveChanges(categories, listId)
    }

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
        val copy = removeItem(indexedCategory, itemId)
        if (copy.items.isNullOrEmpty()) {
            categories.removeAt(indexedCategory.index)
        } else {
            categories.set(indexedCategory.index, copy)
        }
        saveChanges(categories, listId)
    }

    override fun moveItem(cardId: Long, fromPosition: Int, toPosition: Int) {
        val indexedCategory = findCategoryById(travelCheckList.categories, cardId) ?: return
        val categories = travelCheckList.categories.toMutableList()
        val copy = swapItems(indexedCategory, fromPosition, toPosition)
        categories.set(indexedCategory.index, copy)
        saveChanges(categories, listId)
    }

    private fun swapItems(category: IndexedValue<CategoryImpl>, fromPosition: Int, toPosition: Int): CategoryImpl {
        var items = category.value.items.toMutableList()
        Collections.swap(items, fromPosition, toPosition)
        return category.value.copy(items = items.toList())
    }

    private fun checkItemInternal(indexedCategory: IndexedValue<CategoryImpl>, itemId: Long, checked: Boolean): CategoryImpl {
        val items = indexedCategory.value.items.map { item ->
            if (item.id.equals(itemId)) {
                item.copy(checked = checked)
            } else {
                item
            }
        }
        return indexedCategory.value.copy(items = items)
    }

    private fun removeItem(category: IndexedValue<CategoryImpl>, itemId: Long) = category.value.copy(items = category.value.items.filter { it.id != itemId })

    private fun saveChanges(categories: MutableList<CategoryImpl>, listId: String) {
        val travelCheckList = travelCheckList.copy(categories = categories.toList())
        repository.updateList(listId, travelCheckList)
    }

    private fun findCategoryById(categories: List<CategoryImpl>, categoryId: Long) = categories.withIndex().find { it.value.id == categoryId }
}