package com.pietrantuono.entities

interface Category {
    val title: String
    val description: String?
    val items: List<CheckListItem>
    val id: Long?
}