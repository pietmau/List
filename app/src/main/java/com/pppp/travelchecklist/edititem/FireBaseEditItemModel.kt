package com.pppp.travelchecklist.edititem

import com.pppp.travelchecklist.repository.SingleCheckListRepository
import javax.inject.Inject

class FireBaseEditItemModel @Inject constructor(private val singleCheckListRepository: SingleCheckListRepository) : EditItemModel {
}