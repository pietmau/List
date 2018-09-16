package com.pppp.entities

import java.util.Collections.emptyList


data class Category(
    var title: String,
    var description: String?,
    var items: List<CheckListItem> = emptyList()
) {
    constructor() : this("", null, emptyList())

    val key: String get() = title.toKey()
}