package com.pppp.travelchecklist.server.lambdas

import com.amazonaws.lambda.demo.ServerTag
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.s3.AmazonS3
import com.pietrantuono.entities.Category
import com.pppp.travelchecklist.server.dynamodatabase.CategoriesFromTags
import com.pppp.travelchecklist.server.dynamodatabase.DynamoCategoriesFromTags

class GetCategoriesFromTags @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<List<ServerTag>, List<Category>> {
    private val categoriesFromTags: CategoriesFromTags = DynamoCategoriesFromTags()

    override fun handleRequest(tags: List<ServerTag>, context: Context): List<Category> {
        val logger = context.logger
        logger.log("Received event: " + tags);
        return try {
            categoriesFromTags.getCategoriesBasedOnTags(tags)
        } catch (exception: Exception) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
    }

}