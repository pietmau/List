package com.pppp.travelchecklist.list.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListModel
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.list.viewmodel.FirebaseSingleCheckListViewModel
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdder
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdderImpl
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import dagger.Module
import dagger.Provides

@Module
class ViewCheckListModule(private val activity: FragmentActivity) {

    @Provides
    fun provideSingleCheckListViewModel(repo: SingleCheckListRepository): SingleCheckListViewModel = ViewModelProviders.of(
        activity,
        ViewCheckListViewModelFactory(repo)
    )
        .get(FirebaseSingleCheckListViewModel::class.java)

    @Provides
    fun provideCategoryAdder(adder: CategoryAdderImpl): CategoryAdder = adder

    @Provides
    fun provideFirebaseSingleCheckListRepository(): SingleCheckListRepository = FirebaseSingleCheckListRepository()
}

class ViewCheckListViewModelFactory(val repo: SingleCheckListRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FirebaseSingleCheckListViewModel(
        FirebaseSingleCheckListModel(repo)
    ) as T
}