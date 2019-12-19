package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.TagsGroup
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TagsGroupImpl @JvmOverloads constructor(
    override var title: String = "",
    override var description: String? = null,
    override var tags: List<RoomTag> = emptyList(),
    override var exclusive: Boolean = false
) : Parcelable, TagsGroup {
    @get:[Exclude]
    override var id: String = ""
}