package com.pppp.travelchecklist.createlist.model.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.createlist.model.TagsCache

abstract class TagSelectorModelImpl(
    tagsCache: TagsCache,
    private val groupId: String
) :
    ViewModel(), TagSelectorModel {

    override val tagsLivedata: LiveData<MutableMap<Tag, Boolean>>
    override var tags: MutableMap<Tag, Boolean> = mutableMapOf()

    init {
        tagsLivedata = tagsCache.getTags
            .filterInstance<TagsCache.Event.Success>()
            .map { it.result }
            .filterNotNUll()
            .map {
                it.filter { it.id == groupId }
                    .flatMap { it.tags }
                    .map { it to false }
                    .toMap()
                    .toMutableMap()
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

