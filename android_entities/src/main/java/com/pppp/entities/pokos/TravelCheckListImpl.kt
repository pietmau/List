package com.pppp.entities.pokos

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList

data class TravelCheckListImpl @JvmOverloads constructor(override var items: List<CategoryImpl> = emptyList()) : TravelCheckList