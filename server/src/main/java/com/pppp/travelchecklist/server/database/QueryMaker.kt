package com.pppp.travelchecklist.server.database

import com.pietrantuono.entities.Tag

interface QueryMaker {
    fun  getCategoriesByTag(tags: List<Tag>): String
}
