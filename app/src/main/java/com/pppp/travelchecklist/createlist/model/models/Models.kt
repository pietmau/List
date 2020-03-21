package com.pppp.travelchecklist.createlist.model.models

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.pppp.travelchecklist.createlist.model.TagsCache

class ExpectedWeatherModel(val db: TagsCache, val id: String, handle: SavedStateHandle) : TagSelectorModelImpl(db, id, handle)

class AccomodationModel(val db: TagsCache, val id: String, handle: SavedStateHandle) : TagSelectorModelImpl(db, id, handle)

class LongOrShortTripModel(val db: TagsCache, val id: String, handle: SavedStateHandle) : TagSelectorModelImpl(db, id, handle)

class PlannedActivitesModel(val db: TagsCache, val id: String, handle: SavedStateHandle) : TagSelectorModelImpl(db, id, handle)

class WhoIsTravellingModel(val db: TagsCache, id: String, handle: SavedStateHandle) : TagSelectorModelImpl(db, id, handle)

class ExpectedWeatherModelFactory(
    private val db: TagsCache,
    activity: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(activity, null) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return ExpectedWeatherModel(db, "8", handle) as T
    }
}

class LongOrShortTripModelFactory(
    private val db: TagsCache,
    activity: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(activity, null) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T =
        LongOrShortTripModel(db, "11", handle) as T
}

class AccomodationModelFactory(
    private val db: TagsCache,
    activity: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(activity, null) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T =
        AccomodationModel(db, "9", handle) as T
}

class PlannedActivitesModelFactory(
    val db: TagsCache,
    activity: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(activity, null) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T =
        PlannedActivitesModel(db, "10", handle) as T

}

class WhoIsTravellingModelFactory(
    val db: TagsCache,
    activity: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(activity, null) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T =
        WhoIsTravellingModel(db, "7", handle) as T
}
