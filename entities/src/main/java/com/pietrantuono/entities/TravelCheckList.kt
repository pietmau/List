package com.pietrantuono.entities

interface TravelCheckList {
    val id: String?
    val name: String?
    val categories: List<Category>
}