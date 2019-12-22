package com.pppp.travelchecklist.list.bottomdialog

import com.pppp.travelchecklist.repository.SingleCheckListRepository
import javax.inject.Inject

interface CategoryAdder {

    fun addCategory(listId: Long, name: String)

    fun addItem(listId: Long, categoryId: String, name: String)
}

class CategoryAdderImpl @Inject constructor(private val repo: SingleCheckListRepository) : CategoryAdder {

    override fun addItem(listId: Long, categoryId: String, name: String) {
        repo.addNewItemFromTitle(listId, categoryId, name)
    }

    override fun addCategory(listId: Long, name: String) {
        repo.addCategory(listId, name)
    }
}