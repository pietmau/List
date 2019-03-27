package com.pppp.travelchecklist.listgenerator

import com.pppp.entities.pokos.CheckList
import com.pppp.travelchecklist.selector.presenter.SelectionData
import io.reactivex.Observable

interface ListGenerator {
    fun generate(selection: SelectionData): Observable<CheckList>
}