package com.pppp.travelchecklist.newlist.model.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.newlist.model.TagsCache
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class TagSelectorModelImpl(
    private val tagsCache: TagsCache,
    private val groupId: String
) :
    ViewModel(), TagSelectorModel {

    override var tags: MutableMap<Tag, Boolean> = mutableMapOf()

    init {
        tagsCache.getTags.observeForever { event ->
            when (event) {
                is TagsCache.Event.Success -> {
                    event.result ?: return@observeForever
                    tags = event.result
                        .filter { it.id == groupId }
                        .flatMap { it.tags }
                        .map { it to false }
                        .toMap()
                        .toMutableMap()
                }
                is TagsCache.Event.Failure -> throw Exception(event.exception)
            }
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
