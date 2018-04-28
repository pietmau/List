package com.pppp.travelchecklist.database

import io.reactivex.observers.DisposableObserver


interface TravelChecklistDatabase {

    fun unsubscribe()

    fun getCountries(observer: DisposableObserver<List<Country>>)
}