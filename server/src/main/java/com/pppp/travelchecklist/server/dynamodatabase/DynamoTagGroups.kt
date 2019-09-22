package com.pppp.travelchecklist.server.dynamodatabase

import com.amazonaws.lambda.demo.ServerTag
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.database.TagGroups
import com.pppp.travelchecklist.server.pokos.ServerTagsGroup

internal val GROUP_TITLE = "group_title"
internal val GROUP_ID = "group_id"
internal val TAG_TITLE = "tag_title"
internal val TAG_HIDDEN = "tag_hidden"
internal val TAG_ID = "tag_id"
internal val GROUP_EXCLUSIVE = "group_exclusive"
internal val TAGS_TABLE_NAME = "tag_groups"
internal val ITEM_TABLE_NAME = "items"
internal val ITEM_ID = "item_id"
internal val ITEM_TITLE = "item_title"
internal val CATEGORY_TITLE = "category_title"
internal val CATEGORY_ID = "category_id"


class DynamoTagGroups : TagGroups {
    private val client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.EU_WEST_1)
        .build()

    override fun getTagsGroup(): List<TagsGroup> {
        val request = ScanRequest(TAGS_TABLE_NAME)
        val result = client.scan(request)
        val z = result.items
            .groupBy { it[GROUP_ID]?.s }
            .map { getGroup(it) }
        return z
    }

    private fun getGroup(entry: Map.Entry<String?, List<MutableMap<String, AttributeValue>>>): ServerTagsGroup {
        val groupId = requireNotNull(entry.key)
        require(entry.value.isNotEmpty())
        val tags = entry.value.map { getTag(it) }
        val exclusive = entry.value.first().get(GROUP_EXCLUSIVE)?.bool ?: false
        val title = requireNotNull(entry.value.first().get(GROUP_TITLE)?.s)
        return ServerTagsGroup(title = title, tags = tags.toMutableList(), exclusive = exclusive, id = groupId)
    }

    private fun getTag(attributes: MutableMap<String, AttributeValue>): ServerTag {
        val title = requireNotNull(attributes[TAG_TITLE]?.s)
        val hidden: Boolean = attributes[TAG_HIDDEN]?.bool ?: false
        val id = requireNotNull(attributes[TAG_ID]?.s)
        return ServerTag(title, hidden, id)
    }
}