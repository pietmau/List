package com.pppp.travelchecklist.main.view

import com.pppp.travelchecklist.model.CardItemData

interface TravelListView {

    fun render(viewConfiguration: ViewConfiguration)

    data class ViewConfiguration(val status: Status, val items: List<CardItemData>) {

        enum class Status {
            START
        }
    }

    sealed class Action {

    }

}