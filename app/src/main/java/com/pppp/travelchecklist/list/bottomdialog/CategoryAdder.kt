package com.pppp.travelchecklist.list.bottomdialog

import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import javax.inject.Inject

interface CategoryAdder {

    fun addCategory(listId: TravelCheckListImpl, name: String)

    fun addItem(listId: String, categoryId: Long, name: String)
}

class CategoryAdderImpl @Inject constructor(private val repo: SingleCheckListRepository) : CategoryAdder {

    override fun addItem(travelCheckList: String, categoryId: Long, name: String) {
        repo.addItem(travelCheckList, categoryId, name)
    }

    override fun addCategory(travelCheckList: TravelCheckListImpl, categoryName: String) {
        val categoryId = travelCheckList.categories.maxBy { it.id }!!.id + 1
        repo.addCategory(travelCheckList.id!!, categoryId, categoryName)
    }
}