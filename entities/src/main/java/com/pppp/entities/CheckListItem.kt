package com.pppp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Collections.emptyList

@Parcelize
data class CheckListItem(
    var title: String,
    var checked: Boolean = false,
    var priority: Int = 5,
    var description: String?,
    var category: Category,
    var tags: List<Tag>,
    var optional: Boolean = false
) : Parcelable {
    constructor() : this("", false, 5, null, Category(), emptyList())

    var key: String = hashCode().toString()
}

