package com.pppp.travelchecklist.listgenerator

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckList
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.selector.presenter.SelectionData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface ListGenerator {
    fun generate(selection: SelectionData): Single<out List<Category>>
}

class ListGeneratorImpl(private val retrofitClient: Client) : ListGenerator {

    override fun generate(selection: SelectionData) =
        retrofitClient.generateChecklist(selection.toList() as List<TagImpl>)

}