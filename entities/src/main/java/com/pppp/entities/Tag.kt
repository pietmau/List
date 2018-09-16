package com.pppp.entities

data class Tag(
    var title: String
) {
    constructor() : this("")

    val key: String get() = title.toKey()
}