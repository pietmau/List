package com.pppp.travelchecklist.server.dynamodatabase

import com.amazonaws.lambda.demo.ServerTag
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.dynamodatabase.utils.GROUP_EXCLUSIVE
import com.pppp.travelchecklist.server.dynamodatabase.utils.GROUP_ID
import com.pppp.travelchecklist.server.dynamodatabase.utils.GROUP_TITLE
import com.pppp.travelchecklist.server.dynamodatabase.utils.TAGS_TABLE_NAME
import com.pppp.travelchecklist.server.dynamodatabase.utils.TAG_HIDDEN
import com.pppp.travelchecklist.server.dynamodatabase.utils.TAG_ID
import com.pppp.travelchecklist.server.dynamodatabase.utils.TAG_TITLE
import com.pppp.travelchecklist.server.entities.ServerTagsGroup

interface TagGroups {
    fun getTagsGroup(): List<TagsGroup>
}

class DynamoTagGroups : TagGroups {
    private val client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.EU_WEST_1)
        .build()

    override fun getTagsGroup(): List<TagsGroup> {
        val request = ScanRequest(TAGS_TABLE_NAME)
        val result = client.scan(request)
        return result.items
            .groupBy { it[GROUP_ID]?.s }
            .map<String?, List<MutableMap<String, AttributeValue>>, ServerTagsGroup> { getGroup(it) }
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