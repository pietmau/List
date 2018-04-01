package com.pppp.travelchecklist.model.dao

import android.database.sqlite.SQLiteDatabase
import com.pppp.travelchecklist.model.Card
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData

class ListDaoSqlite(
        private val db: SQLiteDatabase,
        private val queryMaker: Querymaker,
        private val deserializer: Deserializer
) : ListDao {

    override fun getCheckListById(checklistId: Long): CheckList {
        val listsCursor = db.rawQuery(queryMaker.getListQuery(checklistId), emptyArray())
        val emptyChecklist = deserializer.getEmptyCheckList(listsCursor)
        var result: CheckList = CheckList("", emptyList(), -1)
        if (emptyChecklist != null) {
            result = CheckList(emptyChecklist.title, getCards(emptyChecklist.id), emptyChecklist.id)
        }
        return result
    }

    override fun getCheckLists(): List<CheckList> {
        var result = mutableListOf<CheckList>()
        val emptyChecklists: List<CheckList> = getEmptyChecklists()
        for (checkList in emptyChecklists) {
            result.add(CheckList(checkList.title, getCards(checkList.id), checkList.id))
        }
        return result.toList()
    }

    private fun getEmptyChecklists(): List<CheckList> {
        val listsCursor = db.rawQuery(queryMaker.GET_LISTS_QUERY, emptyArray())
        return deserializer.getEmptyCheckLists(listsCursor)
    }

    private fun getCards(listId: Long): List<Card> {
        var result = mutableListOf<Card>()
        val emptyCards: List<Card> = deserializer.getEmptyCards(db.rawQuery(queryMaker.getCardsQuery(listId), emptyArray()))
        for (emptycard in emptyCards) {
            result.add(Card(emptycard.title, getItems(emptycard.id), emptycard.id, emptycard.listId))
        }
        return result.toList()
    }

    private fun getItems(id: Long): List<CheckListItemData> {
        var result = mutableListOf<CheckListItemData>()
        result.addAll(deserializer.getItems(db.rawQuery(queryMaker.getitemsQuery(id), emptyArray())))
        return result.toList()
    }

}

