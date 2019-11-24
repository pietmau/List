package com.pppp.travelchecklist.createlist.model.models

import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.createlist.model.TagsCache

abstract class TagSelectorModelImpl(
    tagsCache: TagsCache,
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
