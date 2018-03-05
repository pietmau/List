package com.pppp.travelchecklist.main.view

import com.pppp.travelchecklist.model.CardItemData

interface TravelListView {

    fun render(viewConfiguration: ViewConfiguration)

    abstract sealed class ViewConfiguration(val items: List<CardItemData>) {

        class Start(items: List<CardItemData>) : ViewConfiguration(items) {}

        class DeleteRequest(items: List<CardItemData>) : ViewConfiguration(items) {}
    }

    abstract sealed class Action {

        class StartRequest() : Action()
        class DeleteRequest(val position: Position) : Action()
        class EditRequest(val position: Position) : Action()

        class Position(val cardIndex: Int, val itemIndex: Int)
    }

}