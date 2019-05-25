package com.pppp.travelchecklist.server.items

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.server.database.Dao
import com.pppp.travelchecklist.server.database.DaoImpl

class GetItemsByTag @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<Any?, List<CheckListItem>> {
    private val dao: Dao = DaoImpl()

    override fun handleRequest(any: Any?, context: Context): List<CheckListItem> {
        val logger = context.logger
        return try {
            dao.getItemsWithTags()
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}