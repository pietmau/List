package com.pppp.travelchecklist.server.dynamodatabase

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.GetItemRequest
import com.pietrantuono.entities.Category
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.database.ChecklistItemById
import com.pppp.travelchecklist.server.pokos.ServerCheckListItem

class DynamoChecklistItemById : ChecklistItemById {
    private val docClient = DynamoDB(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build())

    override fun findItemById(itemId: String): Category? {
        val key = hashMapOf(ITEM_ID to AttributeValue().apply { s = itemId })
        val request = GetItemRequest()
            .withTableName(ITEM_TABLE_NAME)
            .withKey(key)
        val itemResult = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.EU_WEST_1)
            .build().getItem(request)
        val title = requireNotNull(itemResult.item[ITEM_TITLE]?.s)
        val categoryId = requireNotNull(itemResult.item[CATEGORY_ID]?.s)
        val categoryTitle = requireNotNull(itemResult.item[CATEGORY_TITLE]?.s)
        val item = ServerCheckListItem(title = title, categoryId = categoryId, id = itemId)
        return ServerCategory(title = categoryTitle, id = categoryId, items = mutableListOf(item))
    }
}