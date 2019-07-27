package com.pppp.travelchecklist.newlist.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.utils.ResourcesWrapper

class SelectorPresenterFactory(
    val resourcesWrapper: ResourcesWrapper,
    val listGenerator: ListGenerator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        NewListPresenter(SelectionData(), resourcesWrapper, listGenerator) as T
}