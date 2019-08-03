package com.pppp.travelchecklist.listgenerator

import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import com.pppp.travelchecklist.newlist.presenter.SelectionData
import io.reactivex.Scheduler
import io.reactivex.Single

interface ListGenerator {
    fun generate(selection: SelectionData, name: String): Single<String>
    fun setName(listid: String, name: String?)
}

class ListGeneratorImpl(
    private val retrofitClient: Client,
    private val travelChecklistRepository: TravelChecklistRepository,
    private val mainThread: Scheduler,
    private val workerThread: Scheduler
) : ListGenerator {

    override fun generate(selection: SelectionData, name: String) =
        retrofitClient
            .generateChecklist(selection.toList() as List<TagImpl>)
            .flatMap { items -> travelChecklistRepository.saveAndGet(items, name) }
            .subscribeOn(workerThread)
            .observeOn(mainThread)

    override fun setName(listId: String, name: String?) {
        travelChecklistRepository.setName(listId, name)
    }
}