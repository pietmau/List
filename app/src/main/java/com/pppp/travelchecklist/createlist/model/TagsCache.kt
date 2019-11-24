package com.pppp.travelchecklist.createlist.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.createlist.model.models.InitialTagsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TagsCacheImpl(
    repository: InitialTagsRepository
) : TagsCache, ViewModel() {
    override val getTags = MutableLiveData<TagsCache.Event>()

    init {
        repository.getTagsGroupCall().enqueue(object : Callback<List<TagsGroup>> {
            override fun onFailure(call: Call<List<TagsGroup>>, t: Throwable) {
                getTags.postValue(TagsCache.Event.Failure(t))
            }

            override fun onResponse(call: Call<List<TagsGroup>>, response: Response<List<TagsGroup>>) {
                getTags.postValue(TagsCache.Event.Success(response.body()))
            }
        })
    }
}

interface TagsCache {
    val getTags: MutableLiveData<Event>

    sealed class Event {
        data class Failure(val exception: Throwable) : Event()
        data class Success(val result: List<TagsGroup>?) : Event()
    }
}

class TagsCacheFactory(private val repository: InitialTagsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = TagsCacheImpl(repository) as T
}