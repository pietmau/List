package com.pppp.travelchecklist.model.dao

import android.provider.BaseColumns
import com.pppp.travelchecklist.model.database.CardContract
import com.pppp.travelchecklist.model.database.ListContract
import com.pppp.travelchecklist.model.database.TravelChecklistItemContract

class Querymaker {

    val GET_LISTS_QUERY: String = "SELECT * FROM " + ListContract.List.TABLE_NAME

    fun getCardsQuery(listId: Long): String = "SELECT * FROM " + CardContract.Card.TABLE_NAME + " WHERE " + CardContract.Card.COLUMN_NAME_LIST_ID + "=" + listId

    fun getitemsQuery(cardId: Long): String = "SELECT * FROM " + TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME + " WHERE " + TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID + "=" + cardId

    fun getListQuery(checklistId: Long): String = "SELECT * FROM " + ListContract.List.TABLE_NAME + " WHERE " + BaseColumns._ID + " = " + checklistId

}