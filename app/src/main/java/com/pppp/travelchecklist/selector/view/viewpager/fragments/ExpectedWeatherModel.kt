package com.pppp.travelchecklist.selector.view.viewpager.fragments

import com.pppp.database.CheckListDatabase

class ExpectedWeatherModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)

class AccomodationModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)

class LongOrShortTripModel(val db: CheckListDatabase, val id: String) : TagSelectorModel(db, id)
