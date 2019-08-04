package com.pppp.travelchecklist.newlist.model.models

import com.pietrantuono.entities.Tag
import io.reactivex.Observable

interface TagSelectorModel {
    fun getTags(): Observable<List<Pair<Tag, Boolean>>>
    fun onTagSelected(tag: Tag)
    fun onTagDeSeleected(tag: Tag)
}