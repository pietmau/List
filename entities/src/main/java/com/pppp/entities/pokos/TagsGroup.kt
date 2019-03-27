package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TagsGroup @JvmOverloads constructor(
    var title: String = "",
    var description: String? = null,
    var tags: List<Tag> = emptyList(),
    var exclusive: Boolean = false) : Parcelable {
    @get:[Exclude]
    var id: String? = null
}