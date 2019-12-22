package com.pppp.travelchecklist.list.model

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDao
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomSingleCheckListUseCase(private val dao: RoomTravelChecklistRepositoryDao) : SingleCheckListUseCase {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun checkItem(listId: Long, cardId: String, itemId: Long, checked: Boolean) {
        coroutineScope.launch {
            dao.getChecklistItemById(itemId)?.let { dao.saveCheckListItem(it.roomCheckListItemProxy.copy(checked = !it.checked)) }
        }
    }

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(listId: String, categoryId: String, itemId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveItem(listId: String, cardId: String, fromPosition: Int, toPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserCheckListAndUpdates(listId: Long): LiveData<RoomTravelCheckList?> = dao.getListById(listId)
}