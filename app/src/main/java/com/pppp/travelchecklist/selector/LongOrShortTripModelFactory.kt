package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.selector.view.viewpager.fragments.LongOrShortTripModel

class LongOrShortTripModelFactory (private val db: CheckListDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        LongOrShortTripModel(db, "is it going to be a long trip?") as T
}
