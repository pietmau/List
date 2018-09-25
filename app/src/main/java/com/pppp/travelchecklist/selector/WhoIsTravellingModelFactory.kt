package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.selector.view.viewpager.fragments.WhoIsTravellingModel

class WhoIsTravellingModelFactory(val db: CheckListDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        WhoIsTravellingModel(
            db,
            "who is travelling? ✈️"
        ) as T
}