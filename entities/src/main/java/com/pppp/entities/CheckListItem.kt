package com.pppp.entities

import java.util.Collections.emptyList

data class CheckListItem(
    var title: String,
    var checked: Boolean = false,
    var priority: Int = 5,
    var description: String?,
    var category: Category,
    var tags: List<Tag>,
    var optional: Boolean = false
) {
    constructor() : this("", false, 5, null, Category(), emptyList())

    val key: String get() = title.toKey()
}

