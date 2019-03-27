package com.amazonaws.lambda.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.S3Event
import com.amazonaws.services.s3.AmazonS3
import com.google.gson.Gson
import com.pietrantuono.entities.Category
import handler.ServerCategory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

private const val TITLE = "title"
private const val DESCRIPTION = "description"
private const val ID = "id"

class LambdaFunctionHandler @JvmOverloads constructor(s3: AmazonS3? = null) :
    RequestHandler<S3Event, List<ServerTag>> {
    private val gson = Gson()
    private lateinit var context: Context
    private val logger
        get() = context.getLogger()

    override fun handleRequest(event: S3Event, context: Context): List<ServerTag> {
        this.context = context
        logger.log("Received event: " + event);
        val connection: Connection
        try {
            connection = openConnection()
        } catch (exception: SQLException) {
            logger.log(exception.stackTrace.toString())
            throw exception
        }
        TODO()
    }

    private fun getCategories(connection: Connection): MutableList<Category> {
        val QUERY = "SELECT * FROM travelchecklist.category;"
        val result = executeQuery(connection, QUERY)
        val categories = mutableListOf<Category>()
        while (result?.next() == true) {
            val title = result.getString(TITLE)
            val description = result.getString(DESCRIPTION)
            val id = result.getInt(ID)
            categories.add(ServerCategory(title, description, id = "I am an id"))
        }
        return categories
    }

    private fun executeQuery(connection: Connection, QUERY: String): ResultSet? {
        val statement = connection.createStatement()
        return statement.executeQuery(QUERY)
    }

    private fun openConnection(): Connection {
        val url =
            "jdbc:mysql://checklistappdatabase.cqe6ft379bmn.eu-west-1.rds.amazonaws.com:3306/"//TODO System.getenv("SERVER_URL")
        val userName = "admin"//TODO System.getenv("USER")
        val password = System.getenv("AWS_DB_PW")
        return DriverManager.getConnection(url, userName, password)
    }
}