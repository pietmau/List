package com.pppp.entities


class Category() {
    lateinit var title: String
    lateinit var items: List<CheckListItem>
    lateinit var key: String
    lateinit var checkListKey: String

    override fun equals(other: Any?) = key.equals((other as Category).key)

    override fun hashCode() = key.hashCode()
}