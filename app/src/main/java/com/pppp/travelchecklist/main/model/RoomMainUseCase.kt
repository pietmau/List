package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.NullPointerException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RoomMainUseCase @Inject constructor(
    private val repo: TravelChecklistRepository,
    private val coroutineContext: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : MainUseCase {

    override fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) { //TODO change
//        Completable.fromCallable {
//            repo.getUsersListsAndUpdates({
//
//            }, {
//
//            })
//        }.subscribeOn(background).subscribe({}, {})
    }

    override fun saveLastVisitedList(listId: Long) {
        coroutineContext.launch {
            repo.saveLastVisitedList(listId)
        }
    }

    override fun getLastVisitedList(success: (id: Long?) -> Unit, failure: ((Throwable?) -> Unit)?) {
        coroutineContext.launch {
            val id = repo.getLastVisitedList()
            withContext(Dispatchers.Main) {
                id?.let(success) ?: failure?.invoke(LastestListNotFoundException())
            }
        }
    }
}