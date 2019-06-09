package com.pppp.travelchecklist.server.lambdas

import com.amazonaws.lambda.demo.ServerTag
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.database.Dao
import com.pppp.travelchecklist.server.database.DaoImpl

class GetItemsByTag @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<List<ServerTag>, List<Category>> {
    private val dao: Dao = DaoImpl()

    override fun handleRequest(tags: List<ServerTag>, context: Context): List<Category> {
        val logger = context.logger
        return try {
            dao.getItemsByTag(tags)
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}