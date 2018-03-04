package com.pppp.travelchecklist.model


data class CheckListItemData(
        val title: String,
        val checked: Boolean = false,
        val priority: Priority = Priority(5)
)
