package com.pppp.travelchecklist.createlist.model.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.createlist.model.TagsCache
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class TagSelectorModelImpl(
    tagsCache: TagsCache,
    private val groupId: String,
    savedStateHandle: SavedStateHandle,
    coroutineDispatcher: CoroutineContext = Dispatchers.IO
) :
    ViewModel(), TagSelectorModel {
    override val tagsLivedata: LiveData<MutableMap<Tag, Boolean>> = savedStateHandle.getLiveData(groupId)
    override var tags: MutableMap<Tag, Boolean> = mutableMapOf()

    init {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, t -> /* NoOp */ }

        viewModelScope.launch(coroutineExceptionHandler + coroutineDispatcher) {
            val value = tagsCache.getTags(groupId).map { it to false }.toMap().toMutableMap()
            if (tagsLivedata.value == null) {
                (tagsLivedata as MutableLiveData).postValue(value)
            }
        }
    }

    override fun onTagSelected(tag: Tag) {
        setSelected(tag, true)
    }

    private fun setSelected(tag: Tag, selected: Boolean) {
        val value = tagsLivedata.value?.apply {
            set(tag, selected)
        }
        (tagsLivedata as MutableLiveData).postValue(value)
    }

    override fun onTagDeSeleected(tag: Tag) {
        setSelected(tag, false)
    }
}

