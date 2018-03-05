package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.main.view.TravelListView
import io.reactivex.subjects.PublishSubject


class Reducer(private val model: Model) {

    private val subject: PublishSubject<TravelListView.ViewConfiguration> = PublishSubject.create()

    fun reduce(action: TravelListView.Action): TravelListView.ViewConfiguration {
        when (action) {
            is TravelListView.Action.DeleteRequest -> {
                return createDeleteRequestConfiguration()
            }
        }
        return createStartConfiguration()
    }

    private fun createDeleteRequestConfiguration(): TravelListView.ViewConfiguration.DeleteRequest {
        return TravelListView.ViewConfiguration.DeleteRequest(model.getCards())
    }


    private fun createStartConfiguration(): TravelListView.ViewConfiguration.Start {
        return TravelListView.ViewConfiguration.Start(model.getCards())
    }

}