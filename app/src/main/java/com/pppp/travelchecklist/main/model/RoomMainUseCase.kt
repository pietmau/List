package com.pppp.travelchecklist.main.model

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomMainUseCase @Inject constructor(
    private val repo: TravelChecklistRepository,
    private val coroutineContext: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : MainUseCase {

    override fun getUsersLists(): LiveData<List<RoomTravelCheckList?>> = repo.cazz()

    override fun saveLastVisitedList(listId: Long) {
        coroutineContext.launch {
            repo.saveLastVisitedList(listId)
        }
    }

    override fun getLastVisitedList(success: (id: Long?) -> Unit, failure: ((Throwable?) -> Unit)?) {
        coroutineContext.launch {
            val id = repo.getLastVisitedList()
            withContext(Dispatchers.Main) {
                id?.let(success) ?: failure?.invoke(LatestListNotFoundException())
            }
        }
    }
}