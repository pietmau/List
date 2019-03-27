package com.pppp.travelchecklist.server.database

import java.sql.DriverManager

class DatabaseConnectorImpl(
    private val url: String =
        "jdbc:mysql://checklistappdatabase.cqe6ft379bmn.eu-west-1.rds.amazonaws.com:3306/"//TODO System.getenv("SERVER_URL")
    , private val userName: String = "admin"//TODO System.getenv("USER")
    , private val password: String = System.getenv("AWS_DB_PW")
) : DatabaseConnector {
    override fun openConnection() = DriverManager.getConnection(url, userName, password)
}