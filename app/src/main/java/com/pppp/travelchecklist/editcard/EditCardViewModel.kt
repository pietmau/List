package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.ViewIntent
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository

class EditCardViewModel(private val model: EditCardModel = FirebaseEditCardModel(FirebaseSingleCheckListRepository())) :
    ViewActionsConsumer<EditCardViewIntent> {

    override fun accept(editCardViewAction: EditCardViewIntent) =
        when (editCardViewAction) {
            is EditCardViewIntent.DeleteCard -> model.deleteCategory(editCardViewAction.listId, editCardViewAction.categoryId)
        }
}

sealed class EditCardViewIntent : ViewIntent {
    data class DeleteCard(val listId: String, val categoryId: String) : EditCardViewIntent()
}