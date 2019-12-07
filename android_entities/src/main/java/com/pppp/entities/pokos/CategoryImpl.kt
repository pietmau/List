package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.Category
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class CategoryImpl @JvmOverloads constructor(
    override var title: String,
    override var description: String?,
    @Ignore
    override val items: List<CheckListItemImpl> = emptyList(),
    @PrimaryKey(autoGenerate = true)
    override var id: Long?,
    var checkListId: Long
) : Parcelable, Category