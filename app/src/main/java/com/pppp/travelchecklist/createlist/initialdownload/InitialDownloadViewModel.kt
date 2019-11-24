package com.pppp.travelchecklist.createlist.initialdownload

import com.pppp.travelchecklist.createlist.model.TagsCache
import javax.inject.Inject

class InitialDownloadViewModel @Inject constructor(tagsCache: TagsCache) {
    internal val getTags = tagsCache.getTags

}