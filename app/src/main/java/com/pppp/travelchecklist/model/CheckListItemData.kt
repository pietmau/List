package com.pppp.travelchecklist.model

import android.os.Parcel
import android.os.Parcelable


data class CheckListItemData(
        val title: String,
        val checked: Boolean = false,
        val priority: Priority = Priority(5),
        val description: String?,
        val id: Long,
        val cardId: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readParcelable(Priority::class.java.classLoader),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeByte(if (checked) 1 else 0)
        parcel.writeParcelable(priority, flags)
        parcel.writeString(description)
        parcel.writeLong(id)
        parcel.writeLong(cardId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CheckListItemData> {
        override fun createFromParcel(parcel: Parcel): CheckListItemData {
            return CheckListItemData(parcel)
        }

        override fun newArray(size: Int): Array<CheckListItemData?> {
            return arrayOfNulls(size)
        }
    }
}
