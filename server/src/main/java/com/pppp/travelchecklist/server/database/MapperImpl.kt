package com.pppp.travelchecklist.server.database

import categoryId
import categoryTitle
import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.pokos.ServerCheckListItem
import com.pppp.travelchecklist.server.pokos.ServerTagsGroup
import groupId
import groupTitle
import itemId
import itemIsOptional
import itemPriority
import itemTitle
import tagTitle
import tagsId
import java.sql.ResultSet

class MapperImpl : Mapper {

    override fun getTagsGroups(result: ResultSet?): List<TagsGroup> {
        val items = mutableListOf<TagsGroup>()
        items.addAll(getAll(result))
        return items.toList()
    }

    override fun getItemsByTag(result: ResultSet?): List<Category> {
        val categories = HashMap<Long, ServerCategory>()
        while (result?.next() == true) {
            val category: ServerCategory = getCategory(result)
            val item: ServerCheckListItem = getItem(result)
            if (!categories.contains(category.id)) {
                categories.put(category.id, category)
            }
            categories[category.id]?.items?.add(item)
        }
        return categories.values.toList()
    }

    private fun getItem(result: ResultSet) = ServerCheckListItem(
        title = result.getString(itemTitle),
        priority = result.getInt(itemPriority),
        category_id = result.getLong(categoryId),
        id = result.getLong(itemId),
        optional = result.getBoolean(itemIsOptional)
    )

    private fun getCategory(result: ResultSet) = ServerCategory(title = result.getString(categoryTitle), id = result.getLong(categoryId))

    private fun getAll(result: ResultSet?): List<TagsGroup> {
        val groups = HashMap<Long, ServerTagsGroup>()
        while (result?.next() == true) {
            val group = getGroup(result)
            val tag = getTag(result)
            if (!groups.contains(group.id)) {
                groups.put(group.id, group)
            }
            groups[group.id]?.tags?.add(tag)
        }
        return groups.values.toList()
    }

    private fun getTag(result: ResultSet) = ServerTag(title = result.getString(tagTitle), hidden = false, id = result.getLong(tagsId))

    private fun getGroup(result: ResultSet) = ServerTagsGroup(
        title = result.getString(groupTitle),
        description = null,
        tags = mutableListOf(),
        exclusive = false,
        id = result.getLong(groupId)
    )
}