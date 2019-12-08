package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException
import javax.inject.Inject

class RoomMainUseCase @Inject constructor(
    private val repo: TravelChecklistRepository,
    private val background: Scheduler = Schedulers.io()
) : MainUseCase {

    override fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        failure?.invoke(NullPointerException("TODO"))
    }

    override fun saveLastVisitedList(listId: Long) {
        Completable.fromCallable {
            repo.saveLastVisitedList(listId)
        }.subscribeOn(background).subscribe({}, {})
    }

    override fun getLastVisitedList(success: (id: Long) -> Unit?, failure: ((Throwable?) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}