package com.pietrantuono.entities

interface CheckList {
    val title: String
    val categories: List<Category>
    val id: Long?
}