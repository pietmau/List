package com.pppp.travelchecklist.main.view

import com.pppp.travelchecklist.model.CardItemData

interface TravelListView {

    fun render(viewStatus: ViewStatus)

    data class ViewStatus(val status: Status, val items: List<CardItemData>) {

        enum class Status {
            NONE
        }
    }
}