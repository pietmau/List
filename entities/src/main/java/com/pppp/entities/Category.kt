package com.pppp.entities


data class Category(
    var title: String,
    var items: List<CheckListItem>,
    val id: Long,
    val checkListId: Long
)