package com.pppp.entities


data class CheckList(
    val title: String,
    val categories: List<Category>
){
    val key: String get() = title.toKey()
}