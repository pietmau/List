package com.pppp.travelchecklist.server.mapping

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.pppp.travelchecklist.server.database.DatabaseConnector
import com.pppp.travelchecklist.server.database.DatabaseConnectorImpl

class MultipleMappingHandler : RequestHandler<MultipleMapping, List<String>> {

    override fun handleRequest(input: MultipleMapping, context: Context): List<String> {
        val logger = context.logger
        val errors = mutableListOf<String>()
        val connector: DatabaseConnector = DatabaseConnectorImpl()
        connector.openConnection().createStatement().use {
            logger.log(input.toString())
            input.tagsIds.forEach { tagId ->
                val select =
                    "SELECT * FROM travelchecklist.tags_to_items WHERE tag_id = \"$tagId\" AND item_id = \"${input.itemId}\";"
                val result = it.executeQuery(select)
                if (result.next() == true) {
                    errors.add("Already mapped! ${input.itemId} $tagId")
                } else {
                    val insert = "INSERT IGNORE INTO `travelchecklist`.`tags_to_items`\n" +
                            "(`item_id`,\n" +
                            "`item_title`,\n" +
                            "`tag_id`,\n" +
                            "`tag_title`\n" +
                            ")\n" +
                            "VALUE\n" +
                            "(" +
                            input.itemId + "," +
                            "(SELECT title FROM travelchecklist.checklist_item WHERE id=\"${input.itemId}\")," +
                            tagId + "," +
                            "(SELECT title FROM travelchecklist.tags WHERE id=\"${tagId}\"));\n"
                    val z = it.executeUpdate(insert)
                    if (z <= 0) {
                        errors.add("InsertFailed! ${input.itemId} $tagId")
                    }
                }
            }
            return errors
        }
    }
}


