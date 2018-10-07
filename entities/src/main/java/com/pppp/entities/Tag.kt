package com.pppp.entities

import android.os.Parcelable


interface Tag : Parcelable {
    var title: String
    var id: String?
    var hidden: Boolean
}