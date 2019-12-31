package com.pppp.travelchecklist.edititem

import com.pietrantuono.entities.CheckListItem

interface EditItemModel {

    fun retrieveItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)? = null, onSuccess: ((CheckListItem) -> Unit)? = null)

    fun retrieveItemAndUpdates(
        listId: String,
        cardId: String,
        itemId: String,
        onFailure: ((Throwable) -> Unit)? = null,
        onSuccess: ((CheckListItem) -> Unit)? = null
    )

    fun updateItem(
        title: String,
        description: String,
        priority: Int,
        itemId: String,
        listId: String,
        cardId: String,
        alertTimeInMills: Long?
    )
}
