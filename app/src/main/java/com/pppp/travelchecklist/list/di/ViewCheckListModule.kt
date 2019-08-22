package com.pppp.travelchecklist.list.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListModel
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.list.viewmodel.FirebaseSingleCheckListViewModel
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import dagger.Module
import dagger.Provides

@Module
class ViewCheckListModule(private val activity: FragmentActivity) {

    @Provides
    fun provideSingleCheckListViewModel(): SingleCheckListViewModel = ViewModelProviders.of(activity, ViewCheckListViewModelFactory())
        .get(FirebaseSingleCheckListViewModel::class.java)
}

class ViewCheckListViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FirebaseSingleCheckListViewModel(
        FirebaseSingleCheckListModel(FirebaseSingleCheckListRepository())
    ) as T
}