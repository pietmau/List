package com.pppp.travelchecklist.list.model

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCategory
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import java.util.Collections

class FirebaseSingleCheckListUseCase(private val repository: SingleCheckListRepository) : SingleCheckListUseCase {
    override fun getUserCheckListAndUxxpdates(listId: Long): LiveData<RoomTravelCheckList?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var travelCheckList: RoomTravelCheckList

    override fun checkItem(listId: String, cardId: String, itemId: String, checked: Boolean) {
//        val indexedCategory = findCategoryById(travelCheckList.categories, cardId) ?: return
//        val categories = travelCheckList.categories.toMutableList()
//        val copy = checkItemInternal(indexedCategory, itemId, checked)
//        //categories.set(indexedCategory.index, copy)
//        TODO()
//        saveChanges(categories, listId)
        TODO()
    }

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        repository.getUserCheckListAndUpdates(listId, success = {
            this@FirebaseSingleCheckListUseCase.travelCheckList = it as RoomTravelCheckList
            success?.invoke(it)
        }, failure = {
            failure?.invoke(it)
        })
    }

    override fun deleteItem(listId: String, categoryId: String, itemId: String) {
        TODO()
//        val category = (findCategoryById(travelCheckList.categories, categoryId) ?: return).value
//        val index = (findCategoryById(travelCheckList.categories, categoryId) ?: return).index
//        val categories = travelCheckList.categories.toMutableList()
//        categories.set(index, removeItem(category, itemId))
//        saveChanges(categories, listId)
    }

    override fun moveItem(listId: String, cardId: String, fromPosition: Int, toPosition: Int) {

//        val indexedCategory = findCategoryById(travelCheckList.categories, cardId) ?: return
//        val categories = travelCheckList.categories.toMutableList()
//        val copy = swapItems(indexedCategory, fromPosition, toPosition)
//        categories.set(indexedCategory.index, copy)
//        saveChanges(categories, listId)
    }

    private fun swapItems(category: IndexedValue<RoomCategory>, fromPosition: Int, toPosition: Int): RoomCategory {
        var items = category.value.items.toMutableList()
        Collections.swap(items, fromPosition, toPosition)
        return category.value.copy(items = items.toList())
    }

    private fun checkItemInternal(indexedCategory: IndexedValue<RoomCategory>, itemId: String, checked: Boolean): RoomCategory {
        val items = indexedCategory.value.items.map { item ->
            if (requireNotNull(item.id).equals(itemId)) {
                TODO()
                //item.copy(checked = checked)
            } else {
                item
            }
        }
        return indexedCategory.value.copy(items = items)
    }

    private fun removeItem(category: RoomCategory, itemId: String) = category.copy(items = category.items.filter { !requireNotNull(it.id).equals(itemId) })

    private fun saveChanges(categories: List<RoomCategory>, listId: String) {
        //val travelCheckList = travelCheckList.copy(categories = categories.toList())
        repository.updateCategories(listId, travelCheckList)
    }

    private fun findCategoryById(
        categories: List<RoomCategory>,
        categoryId: String
    ): Nothing = TODO()//categories.withIndex().find { it.value.id == categoryId }
}