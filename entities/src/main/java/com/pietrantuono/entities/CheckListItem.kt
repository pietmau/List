package com.pietrantuono.entities

interface CheckListItem {
    var title: String
    var checked: Boolean
    var priority: Int
    var description: String?
    val categoryId: String
    val tags: List<Tag>
    var optional: Boolean
    var id: String
    var alertTimeInMills: Long?
    var isAlertOn: Boolean
}