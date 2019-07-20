package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.Category
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CategoryImpl @JvmOverloads constructor(
    override var title: String = "",
    override var description: String? = null,
    override val items: List<CheckListItemImpl> = emptyList()) : Parcelable, Category {
    override var id: Long = -1
}