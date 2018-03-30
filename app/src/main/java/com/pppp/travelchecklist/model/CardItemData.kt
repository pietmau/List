package com.pppp.travelchecklist.model


data class CardItemData(
        var title: String,
        var items: List<CheckListItemData>,
        val id: Long
)