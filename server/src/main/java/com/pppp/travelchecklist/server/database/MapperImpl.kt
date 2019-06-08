package com.pppp.travelchecklist.server.database

import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.pokos.ServerTagsGroup
import java.sql.ResultSet

class MapperImpl : Mapper {
    private val groupTitle = "groupTitle"
    private val tagTitle = "tagTitle"
    private val groupId = "groupId"
    private val tagId = "tagsId"

    override fun getTagsGroups(result: ResultSet?): List<TagsGroup> {
        val items = mutableListOf<TagsGroup>()
        items.addAll(getAll(result))
        return items.toList()
    }

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

    private fun getTag(result: ResultSet): Tag {
        val title = result.getString(tagTitle)
        val id = result.getLong(tagId)
        return ServerTag(title = title, hidden = false, id = id)
    }

    private fun getGroup(result: ResultSet): ServerTagsGroup {
        val title = result.getString(groupTitle)
        val id = result.getLong(groupId)
        return ServerTagsGroup(title = title, description = null, tags = mutableListOf(), exclusive = false, id = id)
    }
}