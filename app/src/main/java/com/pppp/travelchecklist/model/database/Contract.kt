package com.pppp.travelchecklist.model.database

import android.provider.BaseColumns


object CardContract {
    object Card : BaseColumns {
        const val TABLE_NAME = "card"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_LIST_ID = "list_id"
    }

    const val SQL_CREATE =
            "CREATE TABLE ${Card.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Card.COLUMN_NAME_TITLE} TEXT," +
                    "${Card.COLUMN_NAME_LIST_ID} INTEGER" + ")"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Card.TABLE_NAME}"
}

object TravelChecklistItemContract {
    object TravelChecklistItem : BaseColumns {
        const val TABLE_NAME = "check_list_item"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DESCRIPTION = "title"
        const val COLUMN_NAME_PRIORITY = "priority"
        const val COLUMN_NAME_CHECKED = "checked"
        const val COLUMN_NAME_CARD_ID = "card_id"
    }

    const val SQL_CREATE =
            "CREATE TABLE ${TravelChecklistItem.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${TravelChecklistItem.COLUMN_NAME_TITLE} TEXT," +
                    "${TravelChecklistItem.COLUMN_NAME_DESCRIPTION} TEXT," +
                    "${TravelChecklistItem.COLUMN_NAME_PRIORITY} INTEGER," +
                    "${TravelChecklistItem.COLUMN_NAME_CHECKED} INTEGER," +
                    "${TravelChecklistItem.COLUMN_NAME_CARD_ID} INTEGER" + ")"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TravelChecklistItem.TABLE_NAME}"
}

object ListContract {
    object List : BaseColumns {
        const val TABLE_NAME = "list"
        const val COLUMN_NAME_TITLE = "title"
    }

    const val SQL_CREATE =
            "CREATE TABLE ${List.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${List.COLUMN_NAME_TITLE} TEXT" + ")"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${List.TABLE_NAME}"
}


