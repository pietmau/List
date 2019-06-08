package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.Tag
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TagImpl @JvmOverloads constructor(
    override var title: String = "",
    override var hidden: Boolean = false
) : Parcelable, Tag {
    @get:[Exclude]
    override var id: Long = -1
}