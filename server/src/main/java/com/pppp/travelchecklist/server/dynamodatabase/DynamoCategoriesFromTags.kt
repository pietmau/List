package com.pppp.travelchecklist.server.dynamodatabase

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.database.CategoriesFromTags
import com.pppp.travelchecklist.server.database.ChecklistItemById

class DynamoCategoriesFromTags(private val checklistItemById: ChecklistItemById = DynamoChecklistItemById()) : CategoriesFromTags {

    override fun getCategoriesBasedOnTags(tags: List<Tag>): List<Category> {
        return tags
            .map { it.id }
            .map { checklistItemById.findItemById(it) as ServerCategory }
            .map { category: ServerCategory -> category.copy(items = mutableListOf()) to category.items.toSet() }
            .toMap()
            .map { (category: ServerCategory, items: Set<CheckListItem>) -> category.copy(items = items.toMutableList()) }
    }
}