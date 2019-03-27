package com.pppp.travelchecklist.selector.presenter

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.utils.ResourcesWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SelectorPresenterFactory(
    val resourcesWrapper: ResourcesWrapper,
    val listGenerator: ListGenerator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SelectorPresenter(
            SelectionData(),
            resourcesWrapper,
            listGenerator,
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        ) as T
}