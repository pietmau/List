package com.pppp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    var title: String,
    var hidden: Boolean = false
) : Parcelable {
    constructor() : this("")

    lateinit var id: String
}