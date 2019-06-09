package com.pppp.travelchecklist.selector.view.viewpager.fragments.models

import android.arch.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.database.CheckListDatabase
import com.pppp.entities.pokos.TagImpl
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class TagSelectorModel(private val db: CheckListDatabase, private val id: Long, private val scheduler: Scheduler = Schedulers.io()) : ViewModel() {

    private val tags: MutableMap<Tag, Boolean> = mutableMapOf()

    fun getTags(): Observable<List<Pair<Tag, Boolean>>> {
        if (!tags.isEmpty()) {
            return Observable.fromIterable(tags.entries.toList())
                .map { it.key to it.value }
                .sorted { o1, o2 -> o1.first.title.compareTo(o2.first.title) }
                .toList()
                .toObservable()
        } else {
            tags.clear()
            return db.getTagGroups()
                .subscribeOn(scheduler)
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .filter { it.id == id }
                .flatMap { Observable.fromIterable(it.tags) }
                .sorted { o1, o2 -> o1.title.compareTo(o2.title) }
                .doOnNext { tags.put(it, false) }
                .map { it to false }
                .toList()
                .toObservable()
        }
    }

    fun onTagSelected(tag: Tag) {
        setSelected(tag, true)
    }

    private fun setSelected(tag: Tag, selected: Boolean) {
        if (tags[tag] != null) {
            tags[tag] = selected
        }
    }

    fun onTagDeSeleected(tag: Tag) {
        setSelected(tag, false)
    }
}
