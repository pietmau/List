package com.pppp.travelchecklist.model.dao

import com.pppp.travelchecklist.model.database.CardContract
import com.pppp.travelchecklist.model.database.TravelChecklistItemContract

class Querymaker {
    val GET_CARDS_QUERY: String = "SELECT " +
            "card.title as card_title, " +
            "check_list_item.* FROM card INNER JOIN check_list_item ON check_list_item.card_id = card._id"
    val GET_LISTS_QUERY: String = "SELECT * FROM list"

    fun getCardsQuery(listId: Long): String? = "SELECT * FROM " + CardContract.Card.TABLE_NAME + " WHERE " + CardContract.Card.COLUMN_NAME_LIST_ID + "=" + listId

    fun getitemsQuery(cardId: Long): String? = "SELECT * FROM " + TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME + " WHERE " + TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID + "=" + cardId
}