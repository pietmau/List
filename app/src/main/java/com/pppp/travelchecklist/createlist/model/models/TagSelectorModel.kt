package com.pppp.travelchecklist.createlist.model.models

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.Tag

interface TagSelectorModel {
    fun onTagSelected(tag: Tag)
    fun onTagDeSeleected(tag: Tag)
    var tags: MutableMap<Tag, Boolean>
    val tagsLivedata: LiveData<MutableMap<Tag, Boolean>>?
}