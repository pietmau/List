package com.pppp.travelchecklist.main.model

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckList

interface MainUseCase {

    fun saveLastVisitedList(it: Long)

    fun getLastVisitedList(success: (id: Long?) -> Unit, failure: ((Throwable?) -> Unit)?)

    fun getUsersLists(): LiveData<List<RoomTravelCheckList?>>

    fun deleteList(id:Long)
}

