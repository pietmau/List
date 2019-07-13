package com.pppp.entities.pokos

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList

data class TravelCheckListImpl(override val items: List<Category>) : TravelCheckList