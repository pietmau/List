package com.pppp.travelchecklist.model.dao

import com.pppp.travelchecklist.model.CheckList


interface ListDao {
    fun getCheckLists(): List<CheckList>
}