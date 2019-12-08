package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.CheckListItem
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class CheckListItemImpl @JvmOverloads constructor(
    override var title: String,
    override var checked: Boolean = false,
    override var priority: Int = 5,
    override var description: String? = null,
    @Ignore
    override var tags: List<TagImpl> = emptyList(),
    override var optional: Boolean = false,
    override var categoryId: Long? = null,
    @PrimaryKey(autoGenerate = true)
    override var id: Long?
) : Parcelable, CheckListItem