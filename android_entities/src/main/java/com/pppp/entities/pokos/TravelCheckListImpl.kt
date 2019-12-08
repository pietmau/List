package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TravelCheckListImpl @JvmOverloads constructor(
    @Relation(
        parentColumn = "id",
        entityColumn = "checkListId"
    )
    override var categories: List<CategoryImpl> = emptyList(),
    @Embedded
    val travelCheckListProxy: TravelCheckListProxy
) : TravelCheckList by travelCheckListProxy, Parcelable

@Entity
@Parcelize
data class TravelCheckListProxy @JvmOverloads constructor(
    @Ignore
    override var categories: List<CategoryImpl> = emptyList(),
    override var name: String? = null,
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    override var accomodation: String? = null,
    override var weather: String? = null,
    override var duration: String? = null,
    @Ignore
    override val plannedActivities: List<String> = emptyList(),
    @Ignore
    override val travellers: List<String> = emptyList(),
    override var destination: String? = null,
    override var checkListId: Long? = null

) : TravelCheckList, Parcelable