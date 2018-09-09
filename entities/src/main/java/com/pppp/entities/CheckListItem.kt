package com.pppp.entities

class CheckListItem() {
    lateinit var title: String
    var checked: Boolean = false
    var priority: Int = 5
    lateinit var description: String
    lateinit var key: String
    lateinit var category: String
    lateinit var tags: List<String>
}

