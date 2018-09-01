package com.pppp.travelchecklist.model.dao

import android.database.Cursor
import android.provider.BaseColumns
import com.pppp.travelchecklist.model.Category
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority
import com.pppp.travelchecklist.model.database.CardContract
import com.pppp.travelchecklist.model.database.ListContract
import com.pppp.travelchecklist.model.database.TravelChecklistItemContract

class DeserializerImpl : Deserializer {

    override fun getEmptyCheckList(listsCursor: Cursor?): CheckList? {
        var result: CheckList? = null
        try {
            if (listsCursor?.moveToFirst() == true) {
                val title = listsCursor.getString(listsCursor.getColumnIndexOrThrow(ListContract.List.COLUMN_NAME_TITLE))
                val id = listsCursor.getLong(listsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                if (title != null && id != null) {
                    result = CheckList(title, emptyList(), id)
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
                val title = listsCursor.getString(listsCursor.getColumnIndexOrThrow(ListContract.List.COLUMN_NAME_TITLE))
                val id = listsCursor.getLong(listsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                if (title != null && id != null) {
                    val checklist = CheckList(title, emptyList(), id)
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
                val title = cardsCursor.getString(cardsCursor.getColumnIndexOrThrow(CardContract.Card.COLUMN_NAME_TITLE))
                val id = cardsCursor.getLong(cardsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                val listId = cardsCursor.getLong(cardsCursor.getColumnIndexOrThrow(CardContract.Card.COLUMN_NAME_LIST_ID))
                if (title != null && id != null && listId != null) {
                    val items = emptyList<CheckListItemData>()
                    val card = Category(title, items, id, listId)
                    result.add(card)
                }
            }
        } finally {
            cardsCursor?.close();
        }
        return result.toList()
    }

    override fun getItems(itemsCursor: Cursor?): Collection<CheckListItemData> {
        var result = mutableListOf<CheckListItemData>()
        try {
            while (itemsCursor?.moveToNext() == true) {
                val title = itemsCursor.getString(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE))
                val id = itemsCursor.getLong(itemsCursor.getColumnIndexOrThrow(BaseColumns._ID))
                val cardId = itemsCursor.getLong(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID))
                val priorityValue = itemsCursor.getInt(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY))
                val checkedValue = itemsCursor.getInt(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED))
                val checked = if (checkedValue == 1) true else if (checkedValue == 0) false else null
                val priority = if (priorityValue != null) Priority(priorityValue) else null
                val description = itemsCursor.getString(itemsCursor.getColumnIndexOrThrow(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION))
                if (title != null && id != null && cardId != null && priority != null && checked != null) {
                    val item = CheckListItemData(title, checked, priority, description, id, cardId)
                    result.add(item)
                }
            }
        } finally {
            itemsCursor?.close();
        }
        return result.toList()
    }
}