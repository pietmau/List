package com.pppp.travelchecklist.repository.room

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomCheckListItemProxy
import com.pppp.entities.pokos.RoomTag
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckListProxy
import com.pppp.travelchecklist.createlist.presenter.Model
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class RoomTravelChecklistRepository(database: RoomTravelChecklistRepositoryDatabase) : TravelChecklistRepository {
    private val dao = database.roomTravelChecklistRepositoryDao()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

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

    override fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        // TODO
    }

    override fun getUsersLists() = dao.getAllListsAndUpdates()

    override fun saveLastVisitedList(listId: Long) = dao.saveLastVisitedList(ListId(listId))

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        failure?.invoke(NullPointerException())
    }

    override fun deleteChecklist(listId: Long) {
        coroutineScope.launch {
            dao.getListByIdSync(listId)?.let { deleteList(it) }
        }
    }

    private fun deleteList(list: RoomTravelCheckList) {
        dao.deleteList(list.travelCheckListProxy)
        list.categories.forEach { category ->
            dao.deleteCategory(category.categoryProxy)
            category.items.forEach { item ->
                dao.deleteItem(item.roomCheckListItemProxy)
                item.tags.forEach{
                    dao.deleteTag(it)
                }
            }
        }
    }

    override suspend fun getLastVisitedList(): Long? {
        return dao.getLastVisitedId()?.value
    }
}