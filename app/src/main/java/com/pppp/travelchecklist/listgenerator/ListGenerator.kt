package com.pppp.travelchecklist.listgenerator

import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import com.pppp.travelchecklist.newlist.presenter.SelectionData
import io.reactivex.Scheduler
import io.reactivex.Single

interface ListGenerator {
    fun generate(selection: SelectionData): Single<String>
}

class ListGeneratorImpl(
    private val retrofitClient: Client,
    private val travelChecklistRepository: TravelChecklistRepository,
    private val mainThread: Scheduler,
    private val workerThread: Scheduler
) : ListGenerator {

    override fun generate(selection: SelectionData) =
        retrofitClient
            .generateChecklist(selection.toList() as List<TagImpl>)
            .flatMap { travelChecklistRepository.saveAndGet(it) }
            .subscribeOn(workerThread)
            .observeOn(mainThread)
}