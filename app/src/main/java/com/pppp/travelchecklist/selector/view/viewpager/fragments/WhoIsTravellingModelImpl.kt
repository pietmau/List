package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.arch.lifecycle.ViewModel
import com.pppp.database.CheckListDatabase
import com.pppp.entities.Tag
import io.reactivex.Observable

class WhoIsTravellingModelImpl(private val db: CheckListDatabase) : WhoIsTravellingModel,
    ViewModel() {

    private val tags: MutableMap<Tag, Boolean> = mutableMapOf()

    override fun getWhoIsTravelling(): Observable<List<Tag>> {
        if (!tags.isEmpty()) {
            return Observable.just()
        } else {
            tags.clear()
            db.getTagGroups()
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .filter { it.title.equals(WHO_IS_TRAVELLING_TITLE, true) }
                .map { it.tags }
                .flatMap { Observable.fromIterable(it) }
                .sorted { o1, o2 -> o1.title.compareTo(o2.title) }
                .doOnNext { tags.put }
        }
    }

    override fun onWhoisTravellingSelected(traveller: Tag) {
        tags[traveller.title]?.
    }

    companion object {
        const val WHO_IS_TRAVELLING_TITLE = "who is travelling? ✈️"
    }
}