package com.pppp.travelchecklist.newlist.model.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExpectedWeatherModel(val db: InitialTagsRepository, val id: Long) : TagSelectorModel(db, id)

class AccomodationModel(val db: InitialTagsRepository, val id: Long) : TagSelectorModel(db, id)

class LongOrShortTripModel(val db: InitialTagsRepository, val id: Long) : TagSelectorModel(db, id)

class PlannedActivitesModel(val db: InitialTagsRepository, val id: Long) : TagSelectorModel(db, id)

class WhoIsTravellingModel(val db: InitialTagsRepository, id: Long) : TagSelectorModel(db, id)

class ExpectedWeatherModelFactory(private val db: InitialTagsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ExpectedWeatherModel(db, 8) as T
}

class LongOrShortTripModelFactory(private val db: InitialTagsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        LongOrShortTripModel(db, 11) as T
}

class AccomodationModelFactory(private val db: InitialTagsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AccomodationModel(db, 9) as T
}

class PlannedActivitesModelFactory(val db: InitialTagsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PlannedActivitesModel(db, 10) as T
}

class WhoIsTravellingModelFactory(val db: InitialTagsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        WhoIsTravellingModel(db, 7) as T
}
