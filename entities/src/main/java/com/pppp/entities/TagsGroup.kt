package com.pppp.entities

data class TagsGroup(
    var title: String,
    var description: String?,
    var tags: List<Tag>,
    var exclusive: Boolean = false
) {

    constructor() : this("", null, emptyList())

    lateinit var id: String
}