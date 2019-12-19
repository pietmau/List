package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.pietrantuono.entities.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomCategory @JvmOverloads constructor(
    @Embedded
    var categoryProxy: RoomCategoryProxy,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    override var items: List<RoomCheckListItem> = emptyList()
) : Parcelable, Category by categoryProxy

@Entity
@Parcelize
data class RoomCategoryProxy @JvmOverloads constructor(
    override var title: String,
    override var description: String?,
    @Ignore
    override var items: List<RoomCheckListItem> = emptyList(),
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    var checkListId: Long? = null
) : Parcelable, Category
