package com.pppp.travelchecklist.list.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.edititem.EditItemModel
import com.pppp.travelchecklist.edititem.FireBaseEditItemModel
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.list.viewmodel.FireBaseSingleCheckListViewModel
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdder
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdderImpl
import com.pppp.travelchecklist.list.model.RoomSingleCheckListUseCase
import com.pppp.travelchecklist.list.viewmodel.ListSettingsUseCase
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import dagger.Module
import dagger.Provides

@Module
class ViewCheckListModule(private val activity: FragmentActivity) {

    @Provides
    fun provideSingleCheckListViewModel(
        repo: SingleCheckListRepository,
        settingsUseCase: ListSettingsUseCase,
        context: Context
    ): SingleCheckListViewModel = ViewModelProviders.of(
        activity,
        ViewCheckListViewModelFactory(repo, settingsUseCase, context)
    ).get(FireBaseSingleCheckListViewModel::class.java)

    @Provides
    fun provideCategoryAdder(adder: CategoryAdderImpl): CategoryAdder = adder

    @Provides
    fun provideFirebaseSingleCheckListRepository(): SingleCheckListRepository = FirebaseSingleCheckListRepository()

    @Provides
    fun provideListSettingsUseCase(prefreneces: PreferencesWrapper): ListSettingsUseCase = ListSettingsUseCase(prefreneces)

    @Provides
    fun provideEditItemModel(singleCheckListRepository: SingleCheckListRepository): EditItemModel = FireBaseEditItemModel(singleCheckListRepository = singleCheckListRepository)

}

class ViewCheckListViewModelFactory(
    val repo: SingleCheckListRepository,
    val settingsUseCase: ListSettingsUseCase,
    val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FireBaseSingleCheckListViewModel(
            singleCheckListUseCase = RoomSingleCheckListUseCase(context.applicationContext),
            settingsUseCase = settingsUseCase
        ) as T
}