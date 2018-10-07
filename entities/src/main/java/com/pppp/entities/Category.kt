package com.pppp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Collections.emptyList

@Parcelize
data class Category(
    var title: String,
    var description: String?,
    var items: List<CheckListItem> = emptyList()
) : Parcelable {
    constructor() : this("", null, emptyList())

    lateinit var key: String
}