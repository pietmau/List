package com.pppp.entities.pokos

import android.os.Parcelable
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TravelCheckListImpl @JvmOverloads constructor(
    override var categories: List<CategoryImpl> = emptyList(),
    override val name: String? = null,
    override var id: String? = null, // TODO remove this
    override val accomodation: String? = null,
    override val weather: String? = null,
    override val duration: String? = null,
    override val plannedActivities: List<String> = emptyList(),
    override val travellers: List<String> = emptyList(),
    override val destination: String? = null

) : TravelCheckList, Parcelable