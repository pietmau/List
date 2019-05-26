package com.pppp.travelchecklist.server.mapping

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.pppp.travelchecklist.server.database.DatabaseConnector
import com.pppp.travelchecklist.server.database.DatabaseConnectorImpl

class MultipleMappingHandler : RequestHandler<Mapping, List<String>> {

    override fun handleRequest(input: Mapping, context: Context): List<String> {
        val logger = context.logger
        val errors = mutableListOf<String>()
        val connector: DatabaseConnector = DatabaseConnectorImpl()
        connector.openConnection().createStatement().use {
            logger.log(input.toString())
            val delete =
                "DELETE FROM `travelchecklist`.`tags_to_items` WHERE `item_id`=" + input.itemId
            val p = it.execute(delete)
            if (!p) {
                errors.add("Delete failed! ${input.itemId}")
            }
            input.tagsIds.forEach { tagId ->
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
            return errors
        }
    }
}


