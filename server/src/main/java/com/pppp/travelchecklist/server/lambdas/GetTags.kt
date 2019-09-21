package com.pppp.travelchecklist.server.lambdas

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.database.Dao

class GetTags @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<Any?, List<Tag>> {
    private val dao: Dao = TODO()

    override fun handleRequest(any: Any?, context: Context): List<Tag> {
        val logger = context.logger
        return try {
            dao.getTags()
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}