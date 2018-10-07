package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pppp.entities.Tag
import com.pppp.entities.TagsGroup
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TagsGroupImpl @JvmOverloads constructor(override var title: String = "",
                                                   override var description: String? = null,
                                                   override var tags: List<Tag> = emptyList(),
                                                   override var exclusive: Boolean = false) : TagsGroup {
    @get:[Exclude]
    override var id: String? = null
}