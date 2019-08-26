package com.pppp.travelchecklist.list.di

import com.pppp.travelchecklist.list.bottomdialog.AddCategoryBottomDialog
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(ViewCheckListModule::class))
interface ViewCheckListComponent {

    fun inject(viewCheckListFragment: ViewCheckListFragment)

    fun inject(viewCheckListFragment: AddCategoryBottomDialog)
}