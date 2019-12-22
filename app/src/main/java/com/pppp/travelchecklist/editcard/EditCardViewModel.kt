package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.list.model.RoomSingleCheckListRepository
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDatabase

class EditCardViewModel(val database: RoomTravelChecklistRepositoryDatabase) : ViewActionsConsumer<EditCardViewAction> {
    private val model: EditCardModel = TODO()//FirebaseEditCardModel(RoomSingleCheckListRepository(roomTravelChecklistRepositoryDao))

    override fun accept(editCardViewAction: EditCardViewAction) =
        when (editCardViewAction) {
            is EditCardViewAction.DeleteCard -> model.deleteCategory(editCardViewAction.listId, editCardViewAction.categoryId)
        }
}

sealed class EditCardViewAction : ViewAction {
    data class DeleteCard(val listId: String, val categoryId: String) : EditCardViewAction()
}