package com.pppp.travelchecklist.server.database

import com.pietrantuono.entities.Category

interface Dao {
    fun getCategories(): List<Category>
}