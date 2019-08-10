package com.pppp.travelchecklist.newlist.initialdownload

import androidx.lifecycle.MutableLiveData
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.newlist.model.TagsCache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class InitialDownloadViewModel @Inject constructor(tagsCache: TagsCache) {
    internal val getTags = tagsCache.getTags

}