package com.pppp.travelchecklist.server.database

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.server.handler.ServerCategory

const val TITLE = "title"
const val DESCRIPTION = "description"
const val ID = "id"
const val SELECT_FROM_CATEGORY = "SELECT * FROM travelchecklist.category;"

class DaoImpl(
    private val connector: DatabaseConnector = DatabaseConnectorImpl()
) : Dao {
    private val statement get() = connector.openConnection().createStatement()

    override fun getCategories() = queryCategories()

    private fun queryCategories() =
        statement.use {
            val result = it.executeQuery(SELECT_FROM_CATEGORY)
            val categories = mutableListOf<Category>()
            while (result?.next() == true) {
                val title = result.getString(TITLE)
                val description = result.getString(DESCRIPTION)
                val id = result.getInt(ID)
                val items = getItems(id)
                categories.add(ServerCategory(title, description, items, id.toString()))
            }
            categories.toList()
        }

    private fun getItems(id: Int) =
        statement.use {
            val query = "SELECT * FROM travelchecklist.checklist_item WHERE id = $id;"
            val result = it.executeQuery(query)
            val categories = mutableListOf<CheckListItem>()
            while (result?.next() == true) {
                val title = result.getString(TITLE)
                val description = result.getString(DESCRIPTION)
                val id = result.getInt(ID)
                //val items = getItems(id)
                //categories.add(ServerCategory(title, description, items, id.toString()))
            }
            categories.toList()
        }
}