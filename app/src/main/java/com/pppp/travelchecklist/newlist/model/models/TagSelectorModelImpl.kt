package com.pppp.travelchecklist.newlist.model.models

import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class TagSelectorModelImpl(
    private val repository: InitialTagsRepository,
    private val tagId: Long,
    private val scheduler: Scheduler = Schedulers.io()
) :
    ViewModel(), TagSelectorModel {

    private val tags: MutableMap<Tag, Boolean> = mutableMapOf()

    override fun getTags(): Observable<List<Pair<Tag, Boolean>>> {
        if (!tags.isEmpty()) {
            return Observable.fromIterable(tags.entries.toList())
                .map { it.key to it.value }
                .sorted { o1, o2 -> o1.first.title.compareTo(o2.first.title) }
                .toList()
                .toObservable()
        } else {
            tags.clear()
            return repository.getTagGroups()
                .subscribeOn(scheduler)
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .filter { it.id == tagId }
                .flatMap { Observable.fromIterable(it.tags) }
                .doOnNext { tags.put(it, false) }
                .map { it to false }
                .toList()
                .toObservable()
        }
    }

    override fun onTagSelected(tag: Tag) {
        setSelected(tag, true)
    }

    private fun setSelected(tag: Tag, selected: Boolean) {
        if (tags[tag] != null) {
            tags[tag] = selected
        }
    }

    override fun onTagDeSeleected(tag: Tag) {
        setSelected(tag, false)
    }
}
