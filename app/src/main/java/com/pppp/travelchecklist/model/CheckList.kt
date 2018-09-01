package com.pppp.travelchecklist.model


data class CheckList(
    val title: String,
    val categories: List<Category>,
    val id: Long
)