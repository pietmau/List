package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository

class EditCardViewModel(private val model: EditCardModel = FirebaseEditCardModel(FirebaseSingleCheckListRepository())) :
    ViewActionsConsumer<EditCardViewAction> {

    override fun accept(editCardViewAction: EditCardViewAction) =
        when (editCardViewAction) {
            is EditCardViewAction.DeleteCard -> model.deleteCategory(editCardViewAction.listId, editCardViewAction.categoryId)
        }
}

sealed class EditCardViewAction : ViewAction {
    data class DeleteCard(val listId: String, val categoryId: String) : EditCardViewAction()
}