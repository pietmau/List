package com.pppp.travelchecklist.edititem

import com.pietrantuono.entities.CheckListItem
import javax.inject.Inject

class EditItemDialogFragmentPresenter @Inject constructor(private val model: EditItemModel) {

    fun getItem(listId: String, cardId: String, itemId: String, failure: ((Throwable) -> Unit)? = null, success: ((CheckListItem) -> Unit)? = null) {
        model.retrieveItemAndUpdates(listId, cardId, itemId, failure, success)
    }

    fun onSaveClicked(
        title: String,
        description: String,
        priority: Int,
        listId: String,
        cardId: String,
        itemId: String
    ) {
        model.updateItem(title, description, priority, listId, cardId, itemId)
    }

    fun onDateSet(year: Int, monthOfYear: Int, dayOfMonth: Int) {
    }
}