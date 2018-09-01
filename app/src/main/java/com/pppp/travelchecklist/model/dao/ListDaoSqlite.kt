package com.pppp.travelchecklist.model.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.pppp.travelchecklist.model.Category
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.database.TravelChecklistItemContract

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

    private fun getCards(listId: Long): List<Category> {
        var result = mutableListOf<Category>()
        val emptyCategories: List<Category> = deserializer.getEmptyCards(db.rawQuery(queryMaker.getCardsQuery(listId), emptyArray()))
        for (emptycard in emptyCategories) {
            result.add(Category(emptycard.title, getItems(emptycard.id), emptycard.id, emptycard.listId))
        }
        return result.toList()
    }

    private fun getItems(id: Long): List<CheckListItemData> {
        var result = mutableListOf<CheckListItemData>()
        result.addAll(deserializer.getItems(db.rawQuery(queryMaker.getitemsQuery(id), emptyArray())))
        return result.toList()
    }

    override fun editItem(item: CheckListItemData): Int {
        val values = ContentValues()
        values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, item.title)
        values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED, if (item.checked) 1 else 0)
        values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY, item.priority.value)
        values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION, item.description)
        values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, item.cardId)
        return db.update(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, values, "${BaseColumns._ID} = ?", arrayOf(item.id.toString()))
    }

    override fun deleteItem(item: CheckListItemData): Int {
        return db.delete(TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(item.id.toString()))
    }

}

