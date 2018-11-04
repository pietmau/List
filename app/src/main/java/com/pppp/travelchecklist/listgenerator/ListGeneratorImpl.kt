package com.pppp.travelchecklist.listgenerator

import com.google.gson.Gson
import com.pppp.database.CheckListDatabase
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.travelchecklist.api.RetrofitClient
import com.pppp.travelchecklist.selector.presenter.SelectionData
import io.reactivex.Observable

class ListGeneratorImpl(private val db: CheckListDatabase, retrofitClient: RetrofitClient) : ListGenerator {

    override fun generate(selection: SelectionData): Observable<CheckListImpl> {
        val s = Gson().toJson(selection.travellers)
        TODO()
    }
}