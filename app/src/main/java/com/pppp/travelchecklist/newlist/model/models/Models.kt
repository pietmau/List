package com.pppp.travelchecklist.newlist.model.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pppp.travelchecklist.newlist.model.TagsCache

class ExpectedWeatherModel(val db: TagsCache, val id: String) : TagSelectorModelImpl(db, id)

class AccomodationModel(val db: TagsCache, val id: String) : TagSelectorModelImpl(db, id)

class LongOrShortTripModel(val db: TagsCache, val id: String) : TagSelectorModelImpl(db, id)

class PlannedActivitesModel(val db: TagsCache, val id: String) : TagSelectorModelImpl(db, id)

class WhoIsTravellingModel(val db: TagsCache, id: String) : TagSelectorModelImpl(db, id)

class ExpectedWeatherModelFactory(private val db: TagsCache) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ExpectedWeatherModel(db, "8") as T
}

class LongOrShortTripModelFactory(private val db: TagsCache) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        LongOrShortTripModel(db, "11") as T
}

class AccomodationModelFactory(private val db: TagsCache) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AccomodationModel(db, "9") as T
}

class PlannedActivitesModelFactory(val db: TagsCache) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PlannedActivitesModel(db, "10") as T
}

class WhoIsTravellingModelFactory(val db: TagsCache) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        WhoIsTravellingModel(db, "7") as T
}
