package com.pietrantuono.entities

interface TravelCheckList {
    val id: String?
    val name: String?
    val categories: List<Category>
    val accomodation: String?
    val weather: String?
    val duration: String?
    val plannedActivities: List<String>
    val travellers: List<String>
    val destination: String?
}