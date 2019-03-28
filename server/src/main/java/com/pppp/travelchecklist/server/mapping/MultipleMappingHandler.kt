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
        val statement = connector.openConnection().createStatement()
        logger.log(input.toString())
        input.tags.forEach { tag ->
            val select =
                "SELECT * FROM travelchecklist.tags_to_items WHERE tag_title = \"$tag}\" AND item_title = \"${input.item}\";"
            val result = statement.executeQuery(select)
            if (result.next() == true) {
                errors.add("Already mapped! ${input.item} $tag")
            } else {
                val insert = "INSERT IGNORE INTO `travelchecklist`.`tags_to_items`\n" +
                        "(`item_id`,\n" +
                        "`item_title`,\n" +
                        "`tag_id`,\n" +
                        "`tag_title`\n" +
                        ")\n" +
                        "VALUE\n" +
                        "((SELECT id FROM travelchecklist.checklist_item WHERE title=\"${input.item}\"),\n" +
                        "(SELECT title FROM travelchecklist.checklist_item WHERE title=\"${input.item}\"),\n" +
                        "(SELECT id FROM travelchecklist.tags WHERE title=\"${tag}\"),\n" +
                        "(SELECT title FROM travelchecklist.tags WHERE title=\"${tag}\"));\n"
                val z = statement.executeUpdate(insert)
                if (z <= 0) {
                    errors.add("InsertFailed! ${input.item} $tag")
                }
            }
        }
        statement.close()
        return errors
    }
}


