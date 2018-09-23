package com.pppp.entities


data class CheckList(
    val title: String,
    val categories: List<Category>
) {
    lateinit var id: String
}