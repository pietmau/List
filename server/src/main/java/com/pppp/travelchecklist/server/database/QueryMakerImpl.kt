package com.pppp.travelchecklist.server.database

import categoriesByTag
import com.pietrantuono.entities.Tag

class QueryMakerImpl : QueryMaker {
    override fun getCategoriesByTag(tags: List<Tag>) = categoriesByTag + tags.joinToString(separator = " OR ") { " tag_id = " + it.id }
}