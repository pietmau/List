package com.pppp.travelchecklist.list.bottomdialog

import com.pppp.travelchecklist.repository.SingleCheckListRepository
import javax.inject.Inject

interface CategoryAdder {

    fun addCategory(name: String, text: String)
}

class CategoryAdderImpl @Inject constructor(private val repo: SingleCheckListRepository) : CategoryAdder {

    override fun addCategory(listId: String, name: String) {
        repo.addCategory(listId, name)
    }
}