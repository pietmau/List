package com.pppp.travelchecklist.editcard

import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.repository.SingleCheckListRepository

class FirebaseEditCardModel(private val repository: SingleCheckListRepository) : EditCardModel {

    override fun deleteCategory(listId: String, categoryId: Long) {
        repository.getUserCheckListAndUpdates(listId) { list ->
            val categories = list.categories
                .filter { it.id != categoryId }
                .map { it as CategoryImpl }
            val copy = (list as TravelCheckListImpl).copy(categories = categories)
            repository.updateCategories(listId, copy)
        }
    }
}