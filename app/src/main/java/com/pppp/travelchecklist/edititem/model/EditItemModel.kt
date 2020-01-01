package com.pppp.travelchecklist.edititem.model

import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl

interface EditItemModel {

    fun retrieveItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)? = null, onSuccess: ((CheckListItem) -> Unit)? = null)

    fun updateItem(title: String, description: String, priority: String, itemId: CheckListItemImpl)
}
