package com.pppp.travelchecklist.repository.room

import android.content.Context
import androidx.room.Room
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListProxy
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.createlist.presenter.Model
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Single
import java.lang.NullPointerException

class RoomTravelChecklistRepository(val applicationContext: Context) : TravelChecklistRepository {
    private val db = Room.databaseBuilder(
        applicationContext,
        RoomTravelChecklistRepositoryDatabase::class.java, "list_database"
    ).build()

    override fun saveAndGet(list: List<Category>, name: Model): Single<String> {
        return Single.fromCallable {
            val proxy = CheckListProxy(name.listName ?: "")
            db.roomTravelChecklistRepositoryDao().insertCheckListImpl(proxy).toString()
        }
    }

    override fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setName(listId: String, name: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        failure?.invoke(NullPointerException())
    }

    override fun saveLastVisitedList(listId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        failure?.invoke(NullPointerException())
    }

    override fun deleteChecklist(listId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}