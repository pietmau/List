package com.pppp.travelchecklist.main.model

import androidx.lifecycle.LiveData
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomMainUseCase @Inject constructor(
    private val repo: TravelChecklistRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : MainUseCase {

    override fun getUsersLists(): LiveData<List<RoomTravelCheckList?>> = repo.getUsersLists()

    override fun saveLastVisitedList(listId: Long) {
        coroutineScope.launch {
            repo.saveLastVisitedList(listId)
        }
    }

    override fun getLastVisitedList(success: (id: Long) -> Unit, failure: ((Throwable?) -> Unit)?) {
        coroutineScope.launch {
            val id = repo.getLastVisitedList()
            withContext(Dispatchers.Main) {
                id?.let(success) ?: failure?.invoke(LatestListNotFoundException())
            }
        }
    }

    override fun deleteList(id: Long, complete: (() -> Unit)?) {
        coroutineScope.launch {
            repo.deleteChecklist(id)
            withContext(Dispatchers.Main) {
                complete?.invoke()
            }
        }
    }
}