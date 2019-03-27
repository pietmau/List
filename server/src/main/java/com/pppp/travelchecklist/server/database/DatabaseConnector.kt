package com.pppp.travelchecklist.server.database

import java.sql.Connection

interface DatabaseConnector {
    fun openConnection(): Connection
}