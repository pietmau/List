package com.pppp.travelchecklist.listgenerator

import com.pietrantuono.entities.CheckList
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.selector.presenter.SelectionData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ListGeneratorImpl(private val retrofitClient: Client) : ListGenerator {

    override fun generate(selection: SelectionData): Observable<CheckList> {
        retrofitClient.generateChecklist(selection.toList() as List<TagImpl>)
            .subscribeOn(Schedulers.io())
            .subscribe({

            }, {

            })

        return Observable.empty()
    }
}