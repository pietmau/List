package com.pppp.travelchecklist.listgenerator

import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import com.pppp.travelchecklist.createlist.presenter.Model
import io.reactivex.Scheduler
import io.reactivex.Single

interface ListGenerator {
    fun generate(selection: Model, name: String): Single<Long>
    fun setName(listid: String, name: String?)
}

class ListGeneratorImpl(
    private val retrofitClient: Client,
    private val travelChecklistRepository: TravelChecklistRepository,
    private val mainThread: Scheduler,
    private val workerThread: Scheduler
) : ListGenerator {

    override fun generate(selection: Model, name: String) =
        retrofitClient
            .generateChecklist(selection.toList() as List<TagImpl>)
            .flatMap { items -> travelChecklistRepository.saveAndGet(items, selection) }
            .subscribeOn(workerThread)
            .observeOn(mainThread)

    override fun setName(listId: String, name: String?) {
        travelChecklistRepository.setName(listId, name)
    }
}