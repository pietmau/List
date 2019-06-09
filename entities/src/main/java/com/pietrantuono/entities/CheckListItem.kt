package com.pietrantuono.entities

interface CheckListItem {
    var title: String
    var checked: Boolean
    var priority: Int
    var description: String?
    val category_id: Long
    val tags: List<Tag>
    var optional: Boolean
    var id: Long
}