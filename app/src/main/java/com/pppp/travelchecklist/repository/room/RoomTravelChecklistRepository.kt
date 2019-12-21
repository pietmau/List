package com.pppp.travelchecklist.repository.room

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomCheckListItemProxy
import com.pppp.entities.pokos.RoomTag
import com.pppp.entities.pokos.RoomTravelCheckListProxy
import com.pppp.travelchecklist.createlist.presenter.Model
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException

class RoomTravelChecklistRepository(database: RoomTravelChecklistRepositoryDatabase) : TravelChecklistRepository {

    private val dao = database.roomTravelChecklistRepositoryDao()

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
        items.forEach { item ->
            val itemId = saveSingleItem(categoryId, item)
            item.tags.forEach { tag -> saveTag(itemId, tag) }
        }
    }

    private fun saveTag(itemId: Long, tag: Tag) {
        val value = RoomTag(title = tag.title, hidden = tag.hidden, itemId = itemId)
        return dao.saveTag(value)
    }

    private fun saveSingleItem(categoryId: Long, item: CheckListItem): Long {
        val priority = item.priority
        val title = item.title
        val checked = item.checked
        val description = item.description
        val optional = item.optional // TODO finish here
        val value = RoomCheckListItemProxy(
            title = title,
            checked = checked,
            priority = priority,
            description = description,
            optional = optional,
            categoryId = categoryId
        )
        return dao.saveCheckListItem(value)
    }

    private fun saveCategory(travelChecklistId: Long, category: Category): Long {
        val title = category.title
        val description = category.description
        val roomCategoryProxy = RoomCategoryProxy(title = title, description = description, checkListId = travelChecklistId)
        return dao.saveCategory(roomCategoryProxy)
    }

    private fun saveTravelCheckList(model: Model): Long {
        val name = model.listName
        val accommodation = model.accomodation?.title
        val weather = model.weather?.title
        val duration = model.duration?.title
        val destination = model.destination?.name
        val plannedActivities = model.plannedActivities.map { it.title }
        val travellers = model.travellers.map { it.title }
        val value = RoomTravelCheckListProxy(
            name = name,
            accommodation = accommodation,
            weather = weather,
            duration = duration,
            destination = destination,
            plannedActivities = plannedActivities,
            travellers = travellers
        )
        return dao.saveTravelChecklist(value)
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
            // dao.geAllLists()
        }.subscribeOn(Schedulers.io())
            .subscribe({ foo ->
                Log.e("foo", foo.toString())
            }, {

            })
    }

    override fun saveLastVisitedList(listId: Long) = dao.saveLastVisitedList(ListId(listId))

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        failure?.invoke(NullPointerException())
    }

    override fun deleteChecklist(listId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getLastVisitedList(): Long? {
        return dao.getLastVisitedId()?.value
    }
}