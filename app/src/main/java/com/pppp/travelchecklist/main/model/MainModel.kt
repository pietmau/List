package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

interface MainModel {

    fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

}
