package com.amazonaws.lambda.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.S3Event
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.Category
import com.pppp.travelchecklist.server.database.Dao
import com.pppp.travelchecklist.server.database.DaoImpl


class LambdaFunctionHandler @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<S3Event, List<Category>> {
    private val dao: Dao = DaoImpl()

    override fun handleRequest(event: S3Event, context: Context): List<Category> {
        val logger = context.logger
        logger.log("Received event: " + event);
        return try {
            dao.getCategories()
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}