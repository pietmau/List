package com.pietrantuono.entities


interface TagsGroup {
    val title: String
    val description: String?
    val tags: List<Tag>
    val exclusive: Boolean
    val id: Long // Unused
}