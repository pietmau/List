package com.pppp.travelchecklist.selector.view.viewpager.fragments.models

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.database.CheckListDatabase

class ExpectedWeatherModel(val db: CheckListDatabase, val title: String) : TagSelectorModel(db, title)

class AccomodationModel(val db: CheckListDatabase, val title: String) : TagSelectorModel(db, title)

class LongOrShortTripModel(val db: CheckListDatabase, val title: String) : TagSelectorModel(db, title)

class PlannedActivitesModel(val db: CheckListDatabase, val title: String) : TagSelectorModel(db, title)

class WhoIsTravellingModel(db: CheckListDatabase, title: String) : TagSelectorModel(db, title)

class ExpectedWeatherModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ExpectedWeatherModel(db, "Expected weather ⛅") as T
}

class LongOrShortTripModelFactory (private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        LongOrShortTripModel(db, "Is it going to be a long trip?") as T
}

class AccomodationModelFactory(private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AccomodationModel(db, "Accommodation \uD83C\uDFE1") as T
}

class PlannedActivitesModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PlannedActivitesModel(db, "Planned activities") as T
}

class WhoIsTravellingModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        WhoIsTravellingModel(db, "Who is travelling? \uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66") as T
}
