package com.pppp.travelchecklist.server.database

import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup

interface TagGroups {
    fun getTagsGroup(): List<TagsGroup>
}

interface CategoriesFromTags {
    fun getCategoriesBasedOnTags(tags: List<Tag>): List<Category>
}

interface ChecklistItemById {
    fun findItemById(itemId: String): Category?
}

interface Dao : TagGroups, CategoriesFromTags {
    fun getTags(): List<Tag>
    fun getItems(): List<CheckListItem>
    fun getAllItems(): List<CheckListItem>
    fun getItemsByTag(tags: List<ServerTag>): List<Category>
}