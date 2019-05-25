package com.pppp.travelchecklist.server.database

import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.pokos.ServerCheckListItem
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
    private val connector: DatabaseConnector = DatabaseConnectorImpl()
) : Dao {
    private val statement get() = connector.openConnection().createStatement()

    override fun getCategories(tags: List<Tag>) = queryCategories()

    override fun getItems() =
        statement.use {
            val query = "SELECT * FROM travelchecklist.checklist_item"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = result.getInt(CATEGORY_ID)
                val element = getItem(result, categoryId.toString())
                items.add(element)
            }
            items.toList()
        }

    override fun getItemsWithTags(): List<CheckListItem> =
        statement.use {
            val query = "SELECT * FROM travelchecklist.checklist_item"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = result.getInt(CATEGORY_ID)
                val element = getItemWithTag(result, categoryId.toString())
                items.add(element)
            }
            items.toList()
        }

    override fun getTags() = statement.use {
        val result = it.executeQuery(SELECT_FROM_TAGS)
        val tags = mutableListOf<Tag>()
        while (result?.next() == true) {
            val title = result.getString(TITLE)
            val id = result.getInt(ID)
            val items = getItemsByCategoryId(id)
            tags.add(ServerTag(title, false, id.toString()))
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
                val id = result.getInt(ID)
                val items = getItemsByCategoryId(id)
                categories.add(ServerCategory(title, description, items, id.toString()))
            }
            categories.toList()
        }

    private fun getItemsByCategoryId(categoryId: Int) =
        statement.use {
            val query =
                "SELECT * FROM travelchecklist.checklist_item WHERE category_id = $categoryId;"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = categoryId.toString()
                val element = getItem(result, categoryId)
                items.add(element)
            }
            items.toList()
        }

    private fun getItemsById(id: Int) =
        statement.use {
            val query = "SELECT * FROM travelchecklist.checklist_item WHERE id = $id;"
            val result = it.executeQuery(query)
            val items = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val categoryId = result.getInt(CATEGORY_ID)
                val element = getItem(result, categoryId.toString())
                items.add(element)
            }
            items.toList()
        }

    private fun getItem(result: ResultSet, categoryId: String): CheckListItem {
        val title = result.getString(TITLE)
        val description = result.getString(DESCRIPTION)
        val id = result.getInt(ID)
        val checked = result.getBoolean(CHECKED)
        val priority = result.getInt(PRIORITY)
        val optional = result.getBoolean(OPTIONAL)
        return ServerCheckListItem(
            title,
            checked,
            priority,
            description,
            categoryId,
            emptyList(),
            optional,
            id.toString()
        )
    }

    private fun getItemWithTag(result: ResultSet, categoryId: String): CheckListItem {
        val title = result.getString(TITLE)
        val description = result.getString(DESCRIPTION)
        val itemId = result.getInt(ID)
        val checked = result.getBoolean(CHECKED)
        val priority = result.getInt(PRIORITY)
        val optional = result.getBoolean(OPTIONAL)
        return ServerCheckListItem(
            title,
            checked,
            priority,
            description,
            categoryId,
            getTagsByItem(itemId),
            optional,
            itemId.toString()
        )
    }

    private fun getTagsByItem(itemId: Int) = statement.use {
        val result = it.executeQuery("SELECT * FROM travelchecklist.tags_to_items WHERE item_id = "+ itemId+";")
        val tags = mutableListOf<Tag>()
        while (result?.next() == true) {
            val title = result.getString("tag_title")
            val id = result.getInt("tag_id")
            tags.add(ServerTag(title, false, id.toString()))
        }
        tags.toList()
    }


}