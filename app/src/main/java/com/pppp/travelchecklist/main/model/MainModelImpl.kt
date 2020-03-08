package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MainModelImpl(private val repo: TravelChecklistRepository) : MainModel {

    override var checkLists: Map<String, TravelCheckList> = mutableMapOf()

    init {
        getUsersLists({
            checkLists = it.filter { checklist -> checklist.id != null }
                .map { checklist -> requireNotNull(checklist.id) to checklist }
                .toMap().toMutableMap()
        })
        getLastVisitedList()
    }

    override fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        repo.getUsersListsAndUpdates(success, failure)
    }

    override fun saveLastVisitedList(listId: String?) {
        listId?.let { repo.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(failure: ((Throwable?) -> Unit)?, success: ((String?) -> Unit)?) {
        repo.getLastVisitedList(failure, {
            success?.invoke(it)
        })
    }

    override suspend fun deleteCurrentList() {
        val id = getLastVisitedListId() ?: return
        repo.deleteChecklist(id)
        (checkLists as MutableMap).remove(id)
    }

    override fun isEmpty() = checkLists.isEmpty()

    override suspend fun getLastVisitedListId(): String? {
        return suspendCancellableCoroutine { continuation ->
            repo.getLastVisitedList({
                continuation.resumeWithException(it)
            }, {
                continuation.resume(it)
            })
        }
    }

}