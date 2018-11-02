package com.amazonaws.lambda.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.S3Event
import com.amazonaws.services.s3.AmazonS3
import com.google.gson.Gson
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class LambdaFunctionHandler @JvmOverloads constructor(s3: AmazonS3? = null) : RequestHandler<S3Event, String> {
    private val gson = Gson()
    private lateinit var context: Context
    private val logger
        get() = context.getLogger()
    //private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    override fun handleRequest(event: S3Event, context: Context): String {
        this.context = context
        //logger.log("Received event: " + event);
        val connection: Connection
        try {
            connection = openConnection()
        } catch (exception: SQLException) {
            return exception.localizedMessage
        }
        logger.log(event.records.toString())
        return getCategories(connection)
        //connection.
        //return ""
        // Get the object from the event and show its content type
        //        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        //        String key = event.getRecords().get(0).getS3().getObject().getKey();
        //        try {
        //            S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
        //            String contentType = response.getObjectMetadata().getContentType();
        //            context.getLogger().log("CONTENT TYPE: " + contentType);
        //            return contentType;
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            context.getLogger().log(String.format(
        //                "Error getting object %s from bucket %s. Make sure they exist and"
        //                + " your bucket is in the same region as this function.", key, bucket));
        //            throw e;
        //        }

    }

    private fun getCategories(connection: Connection): String {
        val QUERY = "SELECT * FROM travelchecklist.category;"
        val result = executeQuery(connection, QUERY)
        val categories = mutableListOf<Category>()
        while (result?.next() == true) {
            val title = result.getString("title")
            val description = result.getString("description")
            val id = result.getInt("id")
            categories.add(Category(title, description, id.toString()))
        }
        return gson.toJson(categories)?:""
    }

    private fun executeQuery(connection: Connection, QUERY: String): ResultSet? {
        val statement = connection.createStatement()
        return statement.executeQuery(QUERY)
    }

    private fun openConnection(): Connection {
        val url = System.getenv("SERVER_URL")
        val userName = System.getenv("USER")
        val password = System.getenv("PW")
        val driver = "com.mysql.jdbc.Driver"
        return DriverManager.getConnection(url, userName, password)
    }
}