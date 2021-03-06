package com.pppp.travelchecklist.list.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.DateAndTimeFormatterImpl
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListModel
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.list.viewmodel.FireBaseSingleCheckListViewModel
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdder
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdderImpl
import com.pppp.travelchecklist.list.viewmodel.ListSettingsUseCase
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewCheckListModule() {

    @Binds
    abstract fun provideCategoryAdder(adder: CategoryAdderImpl): CategoryAdder

    companion object {
        @Provides
        fun provideFirebaseSingleCheckListRepository(): SingleCheckListRepository = FirebaseSingleCheckListRepository()
    }
}
