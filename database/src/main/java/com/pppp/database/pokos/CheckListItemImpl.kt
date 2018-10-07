package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import kotlinx.android.parcel.Parcelize

@Parcelize
class CheckListItemImpl(override var title: String,
                        override var checked: Boolean,
                        override var priority: Int,
                        override var description: String,
                        override var category: Category,
                        override var tags: List<Tag>,
                        override var optional: Boolean) : CheckListItem {
    @Exclude
    override var id: String? = null
}