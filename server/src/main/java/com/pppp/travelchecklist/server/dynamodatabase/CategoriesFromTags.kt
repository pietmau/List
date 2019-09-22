package com.pppp.travelchecklist.server.dynamodatabase

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.dynamodatabase.utils.ChecklistItemById
import com.pppp.travelchecklist.server.dynamodatabase.utils.DyanmoItemIdsFromTags
import com.pppp.travelchecklist.server.dynamodatabase.utils.DynamoChecklistItemById
import com.pppp.travelchecklist.server.dynamodatabase.utils.ItemIdsFromTags

interface CategoriesFromTags {
    fun getCategoriesBasedOnTags(tags: List<Tag>): List<Category>
}

class DynamoCategoriesFromTags(
    private val checklistItemById: ChecklistItemById = DynamoChecklistItemById(),
    private val itemIdsFromTags: ItemIdsFromTags = DyanmoItemIdsFromTags()
) :
    CategoriesFromTags {

    override fun getCategoriesBasedOnTags(tags: List<Tag>) =
        itemIdsFromTags.getItemIdsFromTags(tags).distinct()
            .map { itemId -> checklistItemById.findItemById(itemId) as ServerCategory }
            .map { category: ServerCategory -> category.copy(items = mutableListOf()) to category.items.toSet() }
            .toMap()
            .map { (category: ServerCategory, items: Set<CheckListItem>) -> category.copy(items = items.toMutableList()) }
}