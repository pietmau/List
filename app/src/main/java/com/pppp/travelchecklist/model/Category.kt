package com.pppp.travelchecklist.model


data class Category(
    var title: String,
    var items: List<CheckListItemData>,
    val id: Long,
    val listId: Long
)