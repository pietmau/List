package com.pietrantuono.entities

interface CheckListItem {
    var title: String
    var checked: Boolean
    var priority: Int
    var description: String?
    val categoryId: Long?
    val tags: List<Tag>
    var optional: Boolean
    var id: Long?
}