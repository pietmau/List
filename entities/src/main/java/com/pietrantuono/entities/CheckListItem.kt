package com.pietrantuono.entities

interface CheckListItem {
    var title: String
    var checked: Boolean
    var priority: Int
    var description: String?
    val category_id: String
    val tags: List<Tag>
    var optional: Boolean
    var id: String
}