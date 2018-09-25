package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.selector.view.viewpager.fragments.PlannedActivitesModel

class PlannedActivitesModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PlannedActivitesModel(
            db,
            "planned activities"
        ) as T
}