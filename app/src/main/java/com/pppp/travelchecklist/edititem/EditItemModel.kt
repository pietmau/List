package com.pppp.travelchecklist.edititem

import com.pietrantuono.entities.CheckListItem

interface EditItemModel {

    fun getItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)? = null, onSuccess: ((CheckListItem) -> Unit)? = null)
}
