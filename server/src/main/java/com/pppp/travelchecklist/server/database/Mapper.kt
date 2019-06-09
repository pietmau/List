package com.pppp.travelchecklist.server.database

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.TagsGroup
import java.sql.ResultSet

interface Mapper {
    fun getTagsGroups(result: ResultSet?): List<TagsGroup>
    fun getItemsByTag(result: ResultSet?): List<Category>
}
