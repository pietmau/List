package com.pppp.travelchecklist.list.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.pietrantuono.entities.CheckList
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomSingleCheckListUseCase(val applicationContext: Context) : SingleCheckListUseCase {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val db = Room
        .databaseBuilder(applicationContext, RoomTravelChecklistRepositoryDatabase::class.java, "list_database")
        .build()
        .roomTravelChecklistRepositoryDao()

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(listId: String, categoryId: String, itemId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveItem(listId: String, cardId: String, fromPosition: Int, toPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkItem(listId: String, cardId: String, itemId: String, checked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserCheckListAndUxxpdates(listId: Long): LiveData<TravelCheckListImpl> = db.getListById(listId)
}