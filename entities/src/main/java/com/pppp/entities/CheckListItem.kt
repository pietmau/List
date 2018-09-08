package com.pppp.entities


data class CheckListItem(
    val title: String,
    val checked: Boolean = false,
    val priority: Priority = Priority(5),
    val description: String,
    val id: Long,
    val categoryId: Long,
    val tags: List<Tag>
)

