package com.pppp.travelchecklist.model.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OpenHelper private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"
        private var db: OpenHelper? = null

        private fun INSTANCE(context: Context): OpenHelper {
            if (db == null) {
                db = OpenHelper(context)
            }
            return db!!
        }

        fun DATABASE(context: Context): SQLiteDatabase = INSTANCE(context).writableDatabase
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CardContract.SQL_CREATE)
        db.execSQL(TravelChecklistItemContract.SQL_CREATE)
        db.execSQL(ListContract.SQL_CREATE)
        insertSomeStuff(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(CardContract.SQL_DELETE_ENTRIES)
        db.execSQL(TravelChecklistItemContract.SQL_DELETE_ENTRIES)
        db.execSQL(ListContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    private fun insertSomeStuff(db: SQLiteDatabase) {
        val listValues = ContentValues()
        listValues.put(ListContract.List.COLUMN_NAME_TITLE, "First list")
        val listId = db.insert(ListContract.List.TABLE_NAME, null, listValues)

        var cardvalues = ContentValues()
        cardvalues.put(CardContract.Card.COLUMN_NAME_TITLE, "First card")
        cardvalues.put(CardContract.Card.COLUMN_NAME_LIST_ID, listId)
        var cardId = db.insert(CardContract.Card.TABLE_NAME, null, cardvalues)

        var itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "First Item title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "First Item")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "Other Item title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "Other Item")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        cardvalues = ContentValues()
        cardvalues.put(CardContract.Card.COLUMN_NAME_TITLE, "Second card")
        cardvalues.put(CardContract.Card.COLUMN_NAME_LIST_ID, listId)
        cardId = db.insert(CardContract.Card.TABLE_NAME, null, cardvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "second Item title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "Second Item")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "third Item title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "third Item")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "Fourth Item title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "Fourth Item")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "Fifth Item title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "Fifth Item")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)


        cardvalues = ContentValues()
        cardvalues.put(CardContract.Card.COLUMN_NAME_TITLE, "Third card")
        cardvalues.put(CardContract.Card.COLUMN_NAME_LIST_ID, listId)
        cardId = db.insert(CardContract.Card.TABLE_NAME, null, cardvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        cardvalues = ContentValues()
        cardvalues.put(CardContract.Card.COLUMN_NAME_TITLE, "Fourth card")
        cardvalues.put(CardContract.Card.COLUMN_NAME_LIST_ID, listId)
        cardId = db.insert(CardContract.Card.TABLE_NAME, null, cardvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        cardvalues = ContentValues()
        cardvalues.put(CardContract.Card.COLUMN_NAME_TITLE, "Fifth card")
        cardvalues.put(CardContract.Card.COLUMN_NAME_LIST_ID, listId)
        cardId = db.insert(CardContract.Card.TABLE_NAME, null, cardvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        cardvalues = ContentValues()
        cardvalues.put(CardContract.Card.COLUMN_NAME_TITLE, "Sixth card")
        cardvalues.put(CardContract.Card.COLUMN_NAME_LIST_ID, listId)
        cardId = db.insert(CardContract.Card.TABLE_NAME, null, cardvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

        itemvalues = ContentValues()
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, cardId)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, 0)
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, "title")
        itemvalues.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, 0)
        db.insert(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, null, itemvalues)

    }


}