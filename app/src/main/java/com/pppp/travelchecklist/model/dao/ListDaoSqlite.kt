package com.pppp.travelchecklist.model.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.model.database.TravelChecklistItemContract

class ListDaoSqlite(
    private val db: SQLiteDatabase,
    private val queryMaker: Querymaker,
    private val deserializer: Deserializer
) : ListDao {

    override fun getCheckListById(checklistId: Long): CheckListImpl {
        val listsCursor = db.rawQuery(queryMaker.getListQuery(checklistId), emptyArray())
        val emptyChecklist = deserializer.getEmptyCheckList(listsCursor)
        var result: CheckListImpl = CheckListImpl("", emptyList())
        if (emptyChecklist != null) {
            result = CheckListImpl(emptyChecklist.title, getCards(emptyChecklist.id!!))
            result.id = emptyChecklist.id
        }
        return result
    }

    override fun getCheckLists(): List<CheckListImpl> {
        var result = mutableListOf<CheckListImpl>()
        val emptyChecklists: List<CheckListImpl> = getEmptyChecklists()
        for (checkList in emptyChecklists) {
            val element = CheckListImpl(checkList.title, getCards(checkList.id!!))
            element.id = checkList.id
            result.add(element)
        }
        return result.toList()
    }

    private fun getEmptyChecklists(): List<CheckListImpl> {
        val listsCursor = db.rawQuery(queryMaker.GET_LISTS_QUERY, emptyArray())
        return deserializer.getEmptyCheckLists(listsCursor)
    }

    private fun getCards(listId: String): List<CategoryImpl> {
        var result = mutableListOf<CategoryImpl>()
        val emptyCategories: List<CategoryImpl> =
            deserializer.getEmptyCards(db.rawQuery(queryMaker.getCardsQuery(listId), emptyArray()))
        for (emptycard in emptyCategories) {
            val element = CategoryImpl(emptycard.title, null, getItems(emptycard.id!!))
            element.id = emptycard.id
            result.add(element)
        }
        return result.toList()
    }

    private fun getItems(id: String): List<CheckListItemImpl> {
        var result = mutableListOf<CheckListItemImpl>()
        result.addAll(
            deserializer.getItems(
                db.rawQuery(
                    queryMaker.getitemsQuery(id),
                    emptyArray()
                )
            )
        )
        return result.toList()
    }

    override fun editItem(item: CheckListItemImpl): Int {
        val values = ContentValues()
        values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_TITLE, item.title)
        values.put(
            TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CHECKED,
            if (item.checked) 1 else 0
        )
        values.put(
            TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_PRIORITY,
            item.priority
        )
        values.put(
            TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_DESCRIPTION,
            item.description
        )
        //values.put(TravelChecklistItemContract.TravelChecklistItem.COLUMN_NAME_CARD_ID, item.)
        return db.update(
            TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME,
            values,
            "${BaseColumns._ID} = ?",
            arrayOf(item.id.toString())
        )
    }

    override fun deleteItem(item: CheckListItemImpl): Int {
        return db.delete(
            TravelChecklistItemContract.TravelChecklistItem.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(item.id.toString())
        )
    }

}

