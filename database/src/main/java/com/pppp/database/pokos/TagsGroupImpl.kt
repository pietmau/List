package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.pppp.entities.Tag
import com.pppp.entities.TagsGroup
import kotlinx.android.parcel.Parcelize

@Parcelize
class TagsGroupImpl(override var title: String,
                    override var description: String?,
                    override var tags: List<Tag>,
                    override var exclusive: Boolean) : TagsGroup {
    @Exclude
    override var id: String? =null
}