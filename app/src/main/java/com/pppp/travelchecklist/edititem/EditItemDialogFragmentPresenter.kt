package com.pppp.travelchecklist.edititem

import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import javax.inject.Inject

class EditItemDialogFragmentPresenter @Inject constructor(private val model: EditItemModel, private val timeFormatter: TimeFormatter) {
    private var activated: Boolean? = null
    private var dayOfMonth: Int? = null
    private var monthOfYear: Int? = null
    private var year: Int? = null

    fun getItem(listId: String, cardId: String, itemId: String, failure: ((Throwable) -> Unit)? = null, success: ((CheckListItem, Alert) -> Unit)? = null) {
        model.retrieveItemAndUpdates(listId, cardId, itemId, failure) { item ->
            val checkListItemImpl = item as CheckListItemImpl
            val alert = if (checkListItemImpl.alertTimeInMills != null) checkListItemImpl.alertTimeInMills else System.currentTimeMillis()
            success?.invoke(item, Alert(alert, item.isAlertOn))
        }
    }

    fun onSaveClicked(
        title: String, description: String, priority: Int, listId: String, cardId: String, itemId: String
    ) {
        model.updateItem(title, description, priority, listId, cardId, itemId, null)
    }

    fun onDateSet(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        this.year = year
        this.monthOfYear = monthOfYear
        this.dayOfMonth = dayOfMonth
    }

    fun onAlertActivated(activated: Boolean) {
        this.activated = activated
    }
}