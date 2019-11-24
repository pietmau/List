package com.pppp.travelchecklist.list.bottomdialog

import com.pppp.travelchecklist.repository.SingleCheckListRepository
import javax.inject.Inject

interface CategoryAdder {

    fun addCategory(listId: String, name: String)

    fun addItem(listId: String, categoryId: String, name: String)
}

class CategoryAdderImpl @Inject constructor(private val repo: SingleCheckListRepository) : CategoryAdder {

    override fun addItem(listId: String, categoryId: String, name: String) {
        repo.addNewItemFromTitle(listId, categoryId, name)
    }

    override fun addCategory(listId: String, name: String) {
        repo.addCategory(listId, name)
    }
}