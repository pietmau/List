package com.pppp.travelchecklist.server.database

import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup

interface TagGroups {
    fun getTagsGroup(): List<TagsGroup>
}

interface Dao : TagGroups {
    fun getCategories(tags: List<Tag>): List<Category>
    fun getTags(): List<Tag>
    fun getItems(): List<CheckListItem>
    fun getAllItems(): List<CheckListItem>
    fun getItemsByTag(tags: List<ServerTag>): List<Category>
}