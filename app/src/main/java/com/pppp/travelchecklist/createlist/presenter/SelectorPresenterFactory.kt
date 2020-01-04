package com.pppp.travelchecklist.createlist.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.utils.ResourcesWrapper

class SelectorPresenterFactory(
    val resourcesWrapper: ResourcesWrapper,
    val listGenerator: ListGenerator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        NewListViewModel(Model(listGenerator), resourcesWrapper) as T
}