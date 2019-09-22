package com.pppp.travelchecklist.server.lambdas

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.dynamodatabase.TagGroups
import com.pppp.travelchecklist.server.dynamodatabase.DynamoTagGroups

class GetTagsGroup @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<Any?, List<TagsGroup>> {
    private val dao: TagGroups = DynamoTagGroups()

    override fun handleRequest(any: Any?, context: Context): List<TagsGroup> {
        val logger = context.logger
        return try {
            dao.getTagsGroup()
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}