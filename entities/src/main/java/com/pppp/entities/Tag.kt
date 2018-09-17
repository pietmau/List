package com.pppp.entities

data class Tag(
    var title: String,
    var hidden: Boolean = false
) {
    constructor() : this("")

    val key: String get() = title.toKey()
}