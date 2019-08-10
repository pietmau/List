package com.pppp.travelchecklist.newlist.model.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pietrantuono.entities.Tag
import io.reactivex.Observable

interface TagSelectorModel {
    fun onTagSelected(tag: Tag)
    fun onTagDeSeleected(tag: Tag)
    var tags: MutableMap<Tag, Boolean>
}