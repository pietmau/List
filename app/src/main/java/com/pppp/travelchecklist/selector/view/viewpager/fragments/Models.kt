package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.database.CheckListDatabase

class ExpectedWeatherModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)

class AccomodationModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)

class LongOrShortTripModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)

class PlannedActivitesModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)

class WhoIsTravellingModel(db: CheckListDatabase, id: String) : TagSelectorModel(db, id)

class ExpectedWeatherModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ExpectedWeatherModel(db, "expected weather \uD83C\uDF21️") as T
}

class LongOrShortTripModelFactory (private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        LongOrShortTripModel(db, "is it going to be a long trip?") as T
}

class AccomodationModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AccomodationModel(db, "accommodation") as T
}

class PlannedActivitesModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PlannedActivitesModel(db, "planned activities") as T
}

class WhoIsTravellingModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        WhoIsTravellingModel(db, "who is travelling? ✈️") as T
}
