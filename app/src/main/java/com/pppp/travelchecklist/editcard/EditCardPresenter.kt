package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository

class EditCardPresenter(private val model: EditCardModel = FirebaseEditCardModel(FirebaseSingleCheckListRepository())) :
    Consumer<EditCardPresenter.ViewAction> {

    override fun accept(viewAction: ViewAction) =
        when (viewAction) {
            is ViewAction.DeleteCard -> model.deleteCategory(viewAction.listId, viewAction.categoryId)
        }

    sealed class ViewAction {
        data class DeleteCard(val listId: String, val categoryId: Long) : ViewAction()
    }
}