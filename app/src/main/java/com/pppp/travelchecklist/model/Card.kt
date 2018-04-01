package com.pppp.travelchecklist.model


data class Card(
        var title: String,
        var items: List<CheckListItemData>,
        val id: Long,
        val listId: Long
)