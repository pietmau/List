package com.pppp.travelchecklist.model

import android.os.Parcel
import android.os.Parcelable

data class Priority(val value: Int) : Parcelable {
    val MAX = 10
    val MIN = 0

    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    init {
        if (value < MIN || value > MAX) throw IllegalArgumentException()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Priority> {
        override fun createFromParcel(parcel: Parcel): Priority {
            return Priority(parcel)
        }

        override fun newArray(size: Int): Array<Priority?> {
            return arrayOfNulls(size)
        }
    }
}