package com.pppp.entities

data class Tag(
    var title: String,
    var hidden: Boolean = false
) {
    constructor() : this("")

    lateinit var id: String
}