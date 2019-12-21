package com.pietrantuono.entities

interface TravelCheckList {
    val id: Long?
    val name: String?
    val categories: List<Category>
    val accommodation: String?
    val weather: String?
    val duration: String?
    val plannedActivities: List<String>
    val travellers: List<String>
    val destination: String?
}