package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.dao.ListDao
import io.reactivex.Observable


class BetterModelImpl(private val dao: ListDao) : Model {

    override fun getCards(checklistId: Long): Observable<CheckList> =
            Observable
                    .just(dao.getCheckListById(checklistId))
                    .cache()

    override fun deleteItem(cardPosition: Int, itemPosition: Int) {
        TODO("not implemented")
    }

    override fun getItem(cardPosition: Int, itemPosition: Int): Observable<CheckListItemData> {
        TODO("not implemented")
    }

    override fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int) {
        TODO("not implemented")
    }


}