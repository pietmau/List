package com.pppp.travelchecklist.selector.view.viewpager.fragments.models

import android.arch.lifecycle.ViewModel
import com.pppp.database.CheckListDatabase
import com.pppp.entities.pokos.Tag
import io.reactivex.Observable

abstract class TagSelectorModel(private val db: CheckListDatabase, private val title: String) :
    ViewModel() {

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
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .filter { it.title.equals(title, true) }
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
