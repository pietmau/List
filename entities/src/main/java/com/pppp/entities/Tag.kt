package com.pppp.entities

class Tag() {
    lateinit var title: String
    lateinit var key: String

    override fun equals(other: Any?) = key.equals((other as Category).key)

    override fun hashCode() = key.hashCode()
}