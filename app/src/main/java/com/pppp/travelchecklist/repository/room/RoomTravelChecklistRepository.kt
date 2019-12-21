package com.pppp.travelchecklist.repository.room

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomTravelCheckListProxy
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

    override fun saveAndGet(list: List<Category>, model: Model): Single<Long> {
        return Single.fromCallable {
            val travelChecklistId = saveTravelCheckList(model)
            list.forEach { category ->
                val categoryId = saveCategory(travelChecklistId, category)
                saveItems(categoryId, category.items)
            }
            travelChecklistId
        }
    }

    private fun saveItems(categoryId: Long, items: List<CheckListItem>) {
        items.forEach { item -> saveSingleItem(categoryId, item) }
    }

    private fun saveSingleItem(categoryId: Long, item: CheckListItem) {
        val priority = item.priority
        val title = item.title
        val checked = item.checked
        val description = item.description
        val optional = item.optional // TODO finish here
        val value =
            RoomCheckListItem(title = title, checked = checked, priority = priority, description = description, optional = optional, categoryId = categoryId)
        db.saveCheckListItem(value)
    }

    private fun saveCategory(travelChecklistId: Long, category: Category): Long {
        val title = category.title
        val description = category.description
        val roomCategoryProxy = RoomCategoryProxy(title = title, description = description, checkListId = travelChecklistId)
        return db.saveCategory(roomCategoryProxy)
    }

    private fun saveTravelCheckList(model: Model): Long {
        val name = model.listName
        val accomodation = model.accomodation?.title
        val weather = model.weather?.title
        val duration = model.duration?.title
        val value = RoomTravelCheckListProxy(name = name, accomodation = accomodation, weather = weather, duration = duration)//TODO finish here
        return db.saveTravelChecklist(value)
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