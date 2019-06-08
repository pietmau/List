package com.pppp.travelchecklist.server.database

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup

interface Dao {
    fun getCategories(tags: List<Tag>): List<Category>
    fun getTags(): List<Tag>
    fun getItems(): List<CheckListItem>
    fun getItemsWithTags(): List<CheckListItem>
    fun getTagsGroup(): List<TagsGroup>
}