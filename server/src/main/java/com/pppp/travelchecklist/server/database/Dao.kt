package com.pppp.travelchecklist.server.database

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.Tag

interface Dao {
    fun getCategories(tags: List<Tag>): List<Category>
}