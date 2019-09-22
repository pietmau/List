package com.pppp.travelchecklist.server.dynamodatabase.utils

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.dynamodatabase.ITEM_IDS
import com.pppp.travelchecklist.server.dynamodatabase.SEPARATOR
import com.pppp.travelchecklist.server.dynamodatabase.TAGS_TABLE_NAME
import com.pppp.travelchecklist.server.dynamodatabase.TAG_ID

interface ItemIdsFromTags {
    fun getItemIdsFromTags(tags: List<Tag>): List<String>
}

class DyanmoItemIdsFromTags : ItemIdsFromTags {
    private val client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build()
    private val request = ScanRequest(TAGS_TABLE_NAME)

    override fun getItemIdsFromTags(tags: List<Tag>): List<String> {
        val tagIds = tags.map(Tag::id)
        val scanItems = client.scan(request).items
        return tagIds
            .flatMap { tagId: String ->
                val tags = scanItems.filter { it.matchesTagId(tagId) }
                tags.mapNotNull { it.get(ITEM_IDS)?.s }
            }.flatMap { it.split(SEPARATOR) }
    }

    private fun MutableMap<String, AttributeValue>.matchesTagId(tagId: String) = get(TAG_ID)?.s?.equals(tagId) == true

}