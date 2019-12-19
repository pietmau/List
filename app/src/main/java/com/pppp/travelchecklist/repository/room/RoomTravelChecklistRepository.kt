package com.pppp.travelchecklist.repository.room

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.createlist.presenter.Model
import com.pppp.travelchecklist.repository.TravelCheckListMapper
import com.pppp.travelchecklist.repository.TravelCheckListMapperImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException

class RoomTravelChecklistRepository(val applicationContext: Context, private val mapper: TravelCheckListMapper = TravelCheckListMapperImpl) :
    TravelChecklistRepository {

    private val db = Room
        .databaseBuilder(applicationContext, RoomTravelChecklistRepositoryDatabase::class.java, "list_database")
        .build()
        .roomTravelChecklistRepositoryDao()

    @Suppress("UNCHECKED_CAST")
    override fun saveAndGet(list: List<Category>, model: Model): Single<Long> {
        return Single.fromCallable {
            val checkListImpl = mapper.map(list, model) as RoomTravelCheckList
            val id = db.saveTravelChecklist(checkListImpl.travelCheckListProxy)
            db.insertCategories(checkListImpl.categories.map {  })
            id
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