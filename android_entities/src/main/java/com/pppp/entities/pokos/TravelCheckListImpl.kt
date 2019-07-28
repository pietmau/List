package com.pppp.entities.pokos

import android.os.Parcelable
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TravelCheckListImpl @JvmOverloads constructor(
    override var categories: List<CategoryImpl> = emptyList(),
    override val name: String? = null
) : TravelCheckList, Parcelable