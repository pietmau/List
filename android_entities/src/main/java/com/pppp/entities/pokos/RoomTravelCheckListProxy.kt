package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pietrantuono.entities.TravelCheckList
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class RoomTravelCheckListProxy @JvmOverloads constructor(
    @Ignore
    override var categories: List<RoomCategory> = emptyList(),
    override var name: String? = null,
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    override var accomodation: String? = null,
    override var weather: String? = null,
    override var duration: String? = null,
    override var plannedActivities: List<String> = emptyList(),
    override var travellers: List<String> = emptyList(),
    override var destination: String? = null

) : TravelCheckList, Parcelable