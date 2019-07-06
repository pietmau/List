package com.pppp.travelchecklist.selector.view.viewpager.fragments.models

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ExpectedWeatherModel(val db: CheckListDatabase, val id: Long) : TagSelectorModel(db, id)

class AccomodationModel(val db: CheckListDatabase, val id: Long) : TagSelectorModel(db, id)

class LongOrShortTripModel(val db: CheckListDatabase, val id: Long) : TagSelectorModel(db, id)

class PlannedActivitesModel(val db: CheckListDatabase, val id: Long) : TagSelectorModel(db, id)

class WhoIsTravellingModel(val db: CheckListDatabase, id: Long) : TagSelectorModel(db, id)

class ExpectedWeatherModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ExpectedWeatherModel(db, 8) as T
}

class LongOrShortTripModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        LongOrShortTripModel(db, 11) as T
}

class AccomodationModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AccomodationModel(db, 9) as T
}

class PlannedActivitesModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PlannedActivitesModel(db, 10) as T
}

class WhoIsTravellingModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        WhoIsTravellingModel(db, 7) as T
}
