package com.pietrantuono.entities


interface Category {
    var title: String
    var description: String?
    val items: List<CheckListItem>
    var id: String?
}