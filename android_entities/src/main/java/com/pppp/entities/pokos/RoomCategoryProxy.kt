package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pietrantuono.entities.Category
import kotlinx.android.parcel.Parcelize

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