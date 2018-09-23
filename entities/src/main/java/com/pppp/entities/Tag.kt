package com.pppp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    var title: String,
    var key: String,
    var hidden: Boolean = false
) : Parcelable {
    constructor() : this("", "")

    override fun equals(other: Any?) = key.equals((other as? Tag)?.key)

    override fun hashCode() = key.hashCode()
}