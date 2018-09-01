package com.pppp.travelchecklist.listgenerator

import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.selector.presenter.SelectionData
import io.reactivex.Observable

class ListGeneratorImpl() : ListGenerator {

    override fun generate(selection: SelectionData): Observable<CheckList> {
        return Observable.fromCallable {
            Thread.sleep(3 * 1000)
            CheckList("", emptyList(), 0L)
        }
    }
}