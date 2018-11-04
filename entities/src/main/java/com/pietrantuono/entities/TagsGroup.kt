package com.pietrantuono.entities


interface TagsGroup {
    var title: String
    var description: String?
    val tags: List<Tag>
    var exclusive: Boolean
    var id: String?
}