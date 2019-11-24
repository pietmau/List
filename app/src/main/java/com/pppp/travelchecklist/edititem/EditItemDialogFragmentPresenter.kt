package com.pppp.travelchecklist.edititem

import com.pietrantuono.entities.CheckListItem
import javax.inject.Inject

class EditItemDialogFragmentPresenter @Inject constructor(private val model: EditItemModel) {

    fun getItem(
        listId: String,
        cardId: String,
        itemId: String,
        failure: ((Throwable) -> Unit)? = null,
        success: ((CheckListItem) -> Unit)? = null

    ) {
        model.getItem(listId, cardId, itemId, failure, success)
    }

}