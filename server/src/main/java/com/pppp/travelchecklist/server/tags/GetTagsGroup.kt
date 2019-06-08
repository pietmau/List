package com.pppp.travelchecklist.server.tags

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.database.Dao
import com.pppp.travelchecklist.server.database.DaoImpl

class GetTagsGroup @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<Any?, List<TagsGroup>> {
    private val dao: Dao = DaoImpl()

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