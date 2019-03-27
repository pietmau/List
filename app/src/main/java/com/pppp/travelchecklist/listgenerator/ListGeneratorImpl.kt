package com.pppp.travelchecklist.listgenerator

import com.pppp.database.CheckListDatabase
import com.pppp.entities.pokos.CheckList
import com.pppp.travelchecklist.selector.presenter.SelectionData
import io.reactivex.Observable

class ListGeneratorImpl(private val db: CheckListDatabase) : ListGenerator {

    override fun generate(selection: SelectionData): Observable<CheckList> {
        return Observable.fromCallable {
            TODO()
        }
    }
}