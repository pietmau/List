package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.Consumer

class EditCardPresenter : Consumer<EditCardPresenter.ViewAction> {

    override fun accept(viewAction: ViewAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    sealed class ViewAction {
        data class DeleteCard(val listId: String, val categoryId: Long) : ViewAction()
    }
}