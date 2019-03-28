package com.amazonaws.lambda.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.database.Dao
import com.pppp.travelchecklist.server.database.DaoImpl


class LambdaFunctionHandler @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<List<Tag>, List<Category>> {
    private val dao: Dao = DaoImpl()

    override fun handleRequest(tags: List<Tag>, context: Context): List<Category> {
        val logger = context.logger
        logger.log("Received event: " + tags);
        return try {
            dao.getCategories(tags)
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}