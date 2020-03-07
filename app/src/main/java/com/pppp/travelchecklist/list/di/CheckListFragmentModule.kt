package com.pppp.travelchecklist.list.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListModel
import com.pppp.travelchecklist.list.viewmodel.FireBaseSingleCheckListViewModel
import com.pppp.travelchecklist.list.viewmodel.ListSettingsUseCase
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ViewCheckListModule::class])
class CheckListFragmentModule(private val fragment: Fragment) {

    @CheckListFragmentScope
    @Provides
    fun provideSingleCheckListViewModel(
        repo: SingleCheckListRepository,
        settingsUseCase: ListSettingsUseCase
    ): SingleCheckListViewModel = ViewModelProviders.of(
        fragment,
        ViewCheckListViewModelFactory(repo, settingsUseCase)
    ).get(FireBaseSingleCheckListViewModel::class.java)
}

class ViewCheckListViewModelFactory(
    val repo: SingleCheckListRepository,
    val settingsUseCase: ListSettingsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FireBaseSingleCheckListViewModel(model = FirebaseSingleCheckListModel(repo), settingsUseCase = settingsUseCase) as T
}