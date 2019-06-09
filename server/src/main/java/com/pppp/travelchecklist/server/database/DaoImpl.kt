package com.pppp.travelchecklist.server.database

import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.pokos.ServerCheckListItem
import getCategoriesByTag
import getTagsWithGroup
import java.sql.ResultSet

const val TITLE = "title"
const val DESCRIPTION = "description"
const val ID = "id"
const val SELECT_FROM_CATEGORY = "SELECT * FROM travelchecklist.category;"
const val SELECT_FROM_TAGS = "SELECT * FROM travelchecklist.tags;"
const val CHECKED = "checked"
const val PRIORITY = "priority"
const val OPTIONAL = "optional"
const val CATEGORY_ID = "category_id"

class DaoImpl(
    private val connector: DatabaseConnector = DatabaseConnectorImpl(),
    private val mapper: Mapper = MapperImpl()
) : Dao {

    override fun getItemsByTag(tags: List<Tag>?) =
        statement.use {
            val result = it.executeQuery(getCategoriesByTag)
            mapper.getItemsByTag(result)
        }

    private val statement get() = connector.openConnection().createStatement()

    override fun getCategories(tags: List<Tag>) = queryCategories()

    override fun getTagsGroup(): List<TagsGroup> =
        statement.use {
            val result = it.executeQuery(getTagsWithGroup)
            mapper.getTagsGroups(result);
        }

    override fun getItems() =
        statement.use {
            val query = "SELECT * FROM travelchecklist.checklist_item"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = result.getLong(CATEGORY_ID)
                val element = getItem(result, categoryId)
                items.add(element)
            }
            items.toList()
        }

    override fun getAllItems(): List<CheckListItem> =
        statement.use {
            val query = "SELECT * FROM travelchecklist.checklist_item"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = result.getLong(CATEGORY_ID)
                val element = getItemWithTag(result, categoryId)
                items.add(element)
            }
            items.toList()
        }

    override fun getTags() = statement.use {
        val result = it.executeQuery(SELECT_FROM_TAGS)
        val tags = mutableListOf<Tag>()
        while (result?.next() == true) {
            val title = result.getString(TITLE)
            val id = result.getLong(ID)
            val items = getItemsByCategoryId(id)
            tags.add(ServerTag(title, false, id))
        }
        tags.toList()
    }

    private fun queryCategories() =
        statement.use {
            val result = it.executeQuery(SELECT_FROM_CATEGORY)
            val categories = mutableListOf<Category>()
            while (result?.next() == true) {
                val title = result.getString(TITLE)
                val description = result.getString(DESCRIPTION)
                val id = result.getLong(ID)
                val items = getItemsByCategoryId(id)
                categories.add(ServerCategory(title, description, items.toMutableList(), id))
            }
            categories.toList()
        }

    private fun getItemsByCategoryId(categoryId: Long) =
        statement.use {
            val query =
                "SELECT * FROM travelchecklist.checklist_item WHERE category_id = $categoryId;"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = categoryId
                val element = getItem(result, categoryId)
                items.add(element)
            }
            items.toList()
        }

    private fun getItem(result: ResultSet, categoryId: Long): CheckListItem {
        val title = result.getString(TITLE)
        val description = result.getString(DESCRIPTION)
        val id = result.getLong(ID)
        val priority = result.getInt(PRIORITY)
        val optional = result.getBoolean(OPTIONAL)
        return ServerCheckListItem(
            title,
            false,
            priority,
            description,
            categoryId,
            emptyList(),
            optional,
            id
        )
    }

    private fun getItemWithTag(result: ResultSet, categoryId: Long): CheckListItem {
        val title = result.getString(TITLE)
        val description = result.getString(DESCRIPTION)
        val itemId = result.getLong(ID)
        val priority = result.getInt(PRIORITY)
        val optional = result.getBoolean(OPTIONAL)
        return ServerCheckListItem(
            title,
            false,
            priority,
            description,
            categoryId,
            getTagsByItem(itemId),
            optional,
            itemId
        )
    }

    private fun getTagsByItem(itemId: Long) = statement.use {
        val result = it.executeQuery("SELECT * FROM travelchecklist.tags_to_items WHERE item_id = " + itemId + ";")
        val tags = mutableListOf<Tag>()
        while (result?.next() == true) {
            val title = result.getString("tag_title")
            val id = result.getLong("tag_id")
            tags.add(ServerTag(title, false, id))
        }
        tags.toList()
    }

}