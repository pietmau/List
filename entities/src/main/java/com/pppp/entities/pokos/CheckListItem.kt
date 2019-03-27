package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CheckListItem @JvmOverloads constructor(
    var title: String = "",
    var checked: Boolean = false,
    var priority: Int = 5,
    var description: String? = null,
    var category: Category? = null,
    var tags: List<Tag> = emptyList(),
    var optional: Boolean = false) : Parcelable {

    @get:[Exclude]
    var id: String? = null
}