package com.pppp.database

interface CheckListDatabase : TagsDatabase, ItemsDatabase, CategoriesDatabase, GroupsDatabase,
    CheckListItemDatabase {
    companion object {
        const val TAGS = "tags"
        const val ITEMS = "items"
        const val CATEGORIES = "categories"
        const val GROUPS = "groups"
        const val CHECKLIST_ITEMS = "checklist_items"
    }
}