package com.pppp.database

interface CheckListDatabase : TagsDatabase, ItemsDatabase, CategoriesDatabase, GroupsDatabase {
    companion object {
        const val TAGS = "tags"
        const val ITEMS = "items"
        const val CATEGORIES = "categories"
        const val GROUPS = "groups"
    }
}