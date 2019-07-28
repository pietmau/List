package com.pppp.entities.pokos

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList

data class TravelCheckListImpl @JvmOverloads constructor(
    override var categories: List<CategoryImpl> = emptyList(),
    override val name: String? = null
) : TravelCheckList