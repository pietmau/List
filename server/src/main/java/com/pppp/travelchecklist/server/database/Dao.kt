package com.pppp.travelchecklist.server.database

import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.dynamodatabase.CategoriesFromTags
import com.pppp.travelchecklist.server.dynamodatabase.TagGroups

interface Dao : TagGroups, CategoriesFromTags {
    fun getTags(): List<Tag>
    fun getItems(): List<CheckListItem>
    fun getAllItems(): List<CheckListItem>
    fun getItemsByTag(tags: List<ServerTag>): List<Category>
}