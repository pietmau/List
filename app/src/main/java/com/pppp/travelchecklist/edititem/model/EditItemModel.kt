package com.pppp.travelchecklist.edititem.model

import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl

interface EditItemModel {

    fun retrieveItem(onFailure: ((Throwable) -> Unit)? = null, onSuccess: ((CheckListItem) -> Unit)? = null)

    fun updateItem(item: CheckListItemImpl)
}
