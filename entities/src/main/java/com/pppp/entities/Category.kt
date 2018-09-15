package com.pppp.entities

import java.util.Collections.emptyList


data class Category(
    var title: String,
    var items: List<CheckListItem>
) {
    constructor() : this("", emptyList())
}