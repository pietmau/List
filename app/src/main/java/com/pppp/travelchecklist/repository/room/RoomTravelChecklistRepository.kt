package com.pppp.travelchecklist.repository.room

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListProxy
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.createlist.presenter.Model
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException

class RoomTravelChecklistRepository(val applicationContext: Context) : TravelChecklistRepository {

    private val db = Room
        .databaseBuilder(applicationContext, RoomTravelChecklistRepositoryDatabase::class.java, "list_database")
        .build()
        .roomTravelChecklistRepositoryDao()

    @Suppress("UNCHECKED_CAST")
    override fun saveAndGet(list: List<Category>, name: Model): Single<Long> {
        return Single.fromCallable {
            val proxy = CheckListProxy(requireNotNull(name.listName))
            //val id = db.insertCheckListImpl(proxy)
            //db.insertCategories((list as List<CategoryImpl>).map { it.copy(checkListId = id, id = null) })
            //  id
            TODO()
        }
    }

    override fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setName(listId: String, name: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SuppressLint("CheckResult")
    override fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        Single.fromCallable {
            TODO()
            // db.geAllLists()
        }.subscribeOn(Schedulers.io())
            .subscribe({ foo ->
                Log.e("foo", foo.toString())
            }, {

            })
    }

    override fun saveLastVisitedList(listId: Long) = db.saveLastVisitedList(ListId(listId))

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        failure?.invoke(NullPointerException())
    }

    override fun deleteChecklist(listId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getLastVisitedList(): Long? {
        return db.getLastVisitedId()?.value
    }
}