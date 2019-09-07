package com.pppp.travelchecklist.list.di

import com.pppp.travelchecklist.editcard.EditCardModule
import com.pppp.travelchecklist.list.bottomdialog.AddCategoryBottomDialog
import com.pppp.travelchecklist.editcard.EditCategoryBottomDialog
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(ViewCheckListModule::class, EditCardModule::class))
interface ViewCheckListComponent {

    fun inject(viewCheckListFragment: ViewCheckListFragment)

    fun inject(viewCheckListFragment: AddCategoryBottomDialog)

    fun inject(editCategoryBottomDialog: EditCategoryBottomDialog)
}