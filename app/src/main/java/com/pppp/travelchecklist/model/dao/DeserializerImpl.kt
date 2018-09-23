package com.pppp.travelchecklist.model.dao

import android.database.Cursor
import android.provider.BaseColumns
import com.pppp.entities.Category
import com.pppp.entities.CheckList
import com.pppp.entities.CheckListItem

import com.pppp.travelchecklist.model.database.CardContract
import com.pppp.travelchecklist.model.database.ListContract
import com.pppp.travelchecklist.model.database.TravelChecklistItemContract

class DeserializerImpl : Deserializer {

    override fun getEmptyCheckList(listsCursor: Cursor?): CheckList? {
        var result: CheckList? = null
        try {
            if (listsCursor?.moveToFirst() == true) {
                val title =
                    listsCursor.getString(listsCursor.getColumnIndexOrThrow(ListContract.List.COLUMN_NAME_TITLE))
                val id = listsCursor.getLong(listsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                if (title != null && id != null) {
                    result = CheckList(title, emptyList())
                    result?.id = id.toString()
                }
            }
        } finally {
            listsCursor?.close();
        }
        return result
    }

    override fun getEmptyCheckLists(listsCursor: Cursor?): List<CheckList> {
        var result = mutableListOf<CheckList>()
        try {
            while (listsCursor?.moveToNext() == true) {
                val title =
                    listsCursor.getString(listsCursor.getColumnIndexOrThrow(ListContract.List.COLUMN_NAME_TITLE))
                val id = listsCursor.getLong(listsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                if (title != null && id != null) {
                    val checklist = CheckList(title, emptyList())
                    checklist.id = id.toString()
                    result.add(checklist)
                }
            }
        } finally {
            listsCursor?.close();
        }
        return result.toList()
    }

    override fun getEmptyCards(cardsCursor: Cursor?): List<Category> {
        var result = mutableListOf<Category>()
        try {
            while (cardsCursor?.moveToNext() == true) {
                val title =
                    cardsCursor.getString(cardsCursor.getColumnIndexOrThrow(CardContract.Card.COLUMN_NAME_TITLE))
                val id = cardsCursor.getLong(cardsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                val listId =
                    cardsCursor.getLong(cardsCursor.getColumnIndexOrThrow(CardContract.Card.COLUMN_NAME_LIST_ID))
                if (title != null && id != null && listId != null) {
                    val items = emptyList<CheckListItem>()
                    val card = Category(title, null, items)
                    card.id = id.toString()
                    result.add(card)
                }
            }
        } finally {
            cardsCursor?.close();
        }
        return result.toList()
    }

    override fun getItems(itemsCursor: Cursor?): Collection<CheckListItem> {
        var result = mutableListOf<CheckListItem>()
        try {
            while (itemsCursor?.moveToNext() == true) {
                val title = itemsCursor.getString(
                    itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE)
                )
                val id = itemsCursor.getLong(itemsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                val cardId = itemsCursor.getLong(
                    itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID)
                )
                val priority =
                    itemsCursor.getInt(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY))
                val checkedValue =
                    itemsCursor.getInt(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED))
                val checked =
                    if (checkedValue == 1) true else if (checkedValue == 0) false else null
                val description = itemsCursor.getString(
                    itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION)
                )
                if (title != null && id != null && cardId != null && priority != null && checked != null) {
                    val item = CheckListItem(
                        title,
                        checked,
                        priority,
                        description,
                        Category(),
                        emptyList()
                    )

                    result.add(item)
                }
            }
        } finally {
            itemsCursor?.close();
        }
        return result.toList()
    }
}