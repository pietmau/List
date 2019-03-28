package com.pppp.travelchecklist.server.mapping

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.pppp.travelchecklist.server.database.DatabaseConnector
import com.pppp.travelchecklist.server.database.DatabaseConnectorImpl

class AddMappingHandler : RequestHandler<Mapping, Unit> {
    private val connector: DatabaseConnector = DatabaseConnectorImpl()
    private val statement get() = connector.openConnection().createStatement()

    override fun handleRequest(input: Mapping, context: Context) {
        statement.use {
            val select =
                "SELECT * FROM travelchecklist.tags_to_items WHERE tag_title = \"${input.tag}\" AND item_title = \"${input.item}\";"
            val result = it.executeQuery(select)
            if (result.next() == true) {
                throw UnsupportedOperationException("Already mapped! " + input.toString())
            }
            val insert = "INSERT IGNORE INTO `travelchecklist`.`tags_to_items`\n" +
                    "(`item_id`,\n" +
                    "`item_title`,\n" +
                    "`tag_id`,\n" +
                    "`tag_title`\n" +
                    ")\n" +
                    "VALUE\n" +
                    "((SELECT id FROM travelchecklist.checklist_item WHERE title=\"${input.item}\"),\n" +
                    "(SELECT title FROM travelchecklist.checklist_item WHERE title=\"${input.item}\"),\n" +
                    "(SELECT id FROM travelchecklist.tags WHERE title=\"${input.tag}\"),\n" +
                    "(SELECT title FROM travelchecklist.tags WHERE title=\"${input.tag}\"));\n"
            it.executeUpdate(insert)
        }
    }
}


data class Mapping(val item: String, val tag: String)

